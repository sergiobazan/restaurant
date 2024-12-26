package com.bazan.restaurant.shared.services;

import org.springframework.stereotype.Service;

@Service
public class SlugService implements ISlugService {
    @Override
    public String getSlug(String name, String id) {
        return name.replaceAll(" ", "-").concat("-" + id);
    }
}
