/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.repositorios;

import com.egg.news.entidades.Noticias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author belugallardo
 */
@Repository
public interface NoticiasRepositorio extends JpaRepository<Noticias,Long>{
    
}
