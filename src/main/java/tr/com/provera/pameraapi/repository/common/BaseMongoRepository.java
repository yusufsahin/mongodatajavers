package tr.com.provera.pameraapi.repository.common;

import org.javers.spring.annotation.JaversAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tr.com.provera.pameraapi.exception.EntityNotFoundException;
import tr.com.provera.pameraapi.model.common.BaseDocument;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseMongoRepository<T extends BaseDocument, ID> extends MongoRepository<T, ID> {

    // Custom method to find all documents that are not soft-deleted
    List<T> findAllByIsDeletedFalse();

    // Custom method to find a document by ID only if it's not soft-deleted
    Optional<T> findByIdAndIsDeletedFalse(ID id);

    // Override findById to handle soft-deleted entities as not found
    @Override
    default Optional<T> findById(ID id) {
        return findByIdAndIsDeletedFalse(id)
                .or(() -> Optional.empty());
    }

    // Override findAll to return only non-deleted entities
    @Override
    default List<T> findAll() {
        return findAllByIsDeletedFalse();
    }

    // Override deleteById method for soft delete
    @Override
    @JaversAuditable
    default void deleteById(ID id) {
        findById(id).ifPresent(entity -> {
            entity.setIsDeleted(true);
            save(entity);
        });
    }

    // Override delete method for soft delete
    @Override
    default void delete(T entity) {
        entity.setIsDeleted(true);
        save(entity);
    }

    // Custom save method for handling versioning
    @JaversAuditable
    default T saveWithVersioning(T entity) {
        if (!entity.isNew()) {
            entity.incrementVersion();
        }
        return save(entity);
    }
}

