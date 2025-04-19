package space.araggna.eris.client.ui.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoIcon;
import org.springframework.data.domain.PageRequest;
import space.araggna.eris.base.domain.entity.ErisProduct;
import space.araggna.eris.base.domain.service.ErisProductService;
import space.araggna.eris.base.ui.view.MainLayout;

import java.util.List;

@Route(value = "", layout = MainLayout.class)
@AnonymousAllowed
@PreserveOnRefresh
@PageTitle("Products")
public class MainView extends VerticalLayout {

    private final Scroller scroller = new Scroller();
    private final FlexLayout productContainer = new FlexLayout();
    private final ProgressBar progressBar = new ProgressBar();

    private TextField searchField = new TextField();

    private final int PAGE_SIZE = 20;
    private int currentPage = 0;
    private boolean loading = false;
    private boolean allLoaded = false;

    private final ErisProductService productService;

    public MainView(ErisProductService productService) {
        this.productService = productService;

        setSizeFull();
        configureUI();
        loadProducts(searchField.getValue());
    }

    private void configureUI() {

        searchField = new TextField();
        searchField.setPlaceholder("Search something here");
        searchField.setPrefixComponent(LumoIcon.SEARCH.create());
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.EAGER);
        searchField.addValueChangeListener(event -> loadProducts(event.getValue()));

        scroller.setSizeFull();
        scroller.setContent(productContainer);

        productContainer.setWidthFull();
        productContainer.setFlexWrap(FlexLayout.FlexWrap.WRAP);
        productContainer.setJustifyContentMode(JustifyContentMode.START);
        productContainer.setAlignItems(Alignment.START);

        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);

        add(searchField, scroller, progressBar);

        scroller.getElement().addEventListener("scroll", event -> {

            double scrollHeight = scroller.getElement().getProperty("scrollHeight", 0.0);
            double scrollTop = scroller.getElement().getProperty("scrollTop", 0.0);
            double clientHeight = scroller.getElement().getProperty("clientHeight", 0.0);

            if (!loading && !allLoaded && (scrollTop + clientHeight + 50 >= scrollHeight)) {
                loadProducts(searchField.getValue());
            }

        }).debounce(200);
    }

    private void loadProducts(String search) {
        productContainer.removeAll();

        loading = true;
        boolean searching = false;

        progressBar.setVisible(true);

        List<ErisProduct> productList;

        if (!search.isEmpty()) {
            productList = productService.search(search, PageRequest.of(0, PAGE_SIZE));
            searching = true;
            currentPage = 0;
        } else {
            productList = productService.list(PageRequest.of(currentPage, PAGE_SIZE));
        }

        if (productList.isEmpty()) {
            allLoaded = true;
        } else {
            productList.forEach(product -> productContainer.add(createProductCard(product)));
            if (!searching) {
                currentPage++;
            }
        }

        loading = false;
        progressBar.setVisible(false);

    }

    private Component createProductCard(ErisProduct product) {

        Div card = new Div();
        card.addClassNames(
                "product-card",
                "hoverable-card"
        );

        Icon iconImage = LumoIcon.PHOTO.create();
        iconImage.addClassName("product-image");

        Span nameSpan = new Span(product.getProductName());
        nameSpan.addClassName("product-name");

        Paragraph descParagraph = new Paragraph(product.getProductDesc());

        Button addToCartButton = new Button("Add To Cart");
        addToCartButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        card.add(iconImage, nameSpan, descParagraph, addToCartButton);
        return card;
    }


}
