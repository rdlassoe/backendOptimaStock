package com.hrks.OptimaStock.common.port;

import com.hrks.OptimaStock.common.dto.TypeDocumentDTO;
import java.util.Optional;

public interface TypeDocumentPort {
    Optional<TypeDocumentDTO> findById(Long id);
}