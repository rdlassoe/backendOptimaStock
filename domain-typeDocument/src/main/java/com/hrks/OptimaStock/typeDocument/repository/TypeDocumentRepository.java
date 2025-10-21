package com.hrks.OptimaStock.typeDocument.repository;

import com.hrks.OptimaStock.typeDocument.model.TypeDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TypeDocumentRepository extends JpaRepository<TypeDocument, Long> {
    Optional<TypeDocument> findBytypeDocument(String typeDocument);
}
