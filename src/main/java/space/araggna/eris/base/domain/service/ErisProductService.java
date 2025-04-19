package space.araggna.eris.base.domain.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import space.araggna.eris.base.domain.entity.ErisProduct;
import space.araggna.eris.base.domain.repository.ErisProductRepository;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ErisProductService {

    private final ErisProductRepository productRepository;

    public ErisProductService(ErisProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ErisProduct> list(Pageable pageable) {
        return productRepository.findAllBy(pageable).toList();
    }

    public List<ErisProduct> search(String searchTerm, Pageable pageable) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return list(pageable);
        }
        return productRepository.findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(
                searchTerm, searchTerm, pageable).toList();
    }
}