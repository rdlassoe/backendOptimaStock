package com.hrks.OptimaStock.common.port;

import com.hrks.OptimaStock.common.dto.TypePersonDTO;
import java.util.Optional;

public interface TypePersonPort {
    Optional<TypePersonDTO> findById(Long id);
}
