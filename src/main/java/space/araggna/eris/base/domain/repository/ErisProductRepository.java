package space.araggna.eris.base.domain.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import space.araggna.eris.base.domain.entity.ErisProduct;

import java.util.UUID;

public interface ErisProductRepository extends JpaRepository<ErisProduct, UUID>, JpaSpecificationExecutor<ErisProduct> {

    // Method for lazy loading with pagination
    Slice<ErisProduct> findAllBy(Pageable pageable);
    
    // Method for searching by product name or code
    Slice<ErisProduct> findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(
            String productName, String productCode, Pageable pageable);
}