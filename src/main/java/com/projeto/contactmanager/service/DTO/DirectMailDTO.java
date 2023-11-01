package com.projeto.contactmanager.service.DTO;

import java.util.Objects;

public record DirectMailDTO(Long id, String name, String directMail) {
    public DirectMailDTO{
        Objects.requireNonNull(id);
        Objects.nonNull(name);
        Objects.nonNull(directMail);
    }
}
