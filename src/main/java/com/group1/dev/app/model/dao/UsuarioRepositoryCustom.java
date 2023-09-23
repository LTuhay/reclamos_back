package com.group1.dev.app.model.dao;

import com.group1.dev.app.model.entity.Usuario;

public interface UsuarioRepositoryCustom {
    public Usuario findUser(String username, String password);
}
