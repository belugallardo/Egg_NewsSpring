/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.servicios;

import com.egg.news.entidades.Noticias;
import com.egg.news.excepciones.MyException;
import com.egg.news.repositorios.NoticiasRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author belugallardo
 */
@Service
public class NoticiaServicio {
   
     @Autowired
     NoticiasRepositorio noticiasRepositorio;
    
     
      @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MyException{
        
        validar(titulo, cuerpo);
        
        Noticias noticia = new Noticias();
        noticia.setTitulo(titulo);
        noticia.setCuerpo(cuerpo);
        noticia.setAlta(true);
        noticiasRepositorio.save(noticia);

    }
    
    @Transactional(readOnly = true)
    public List<Noticias> listarNoticias() {

        List<Noticias> noticias = new ArrayList();

        noticias = noticiasRepositorio.findAll();

        return noticias;
    }
    
      @Transactional
    public void modificarNoticia(String titulo, String cuerpo, Long id) throws MyException{
        
        validar(titulo, cuerpo);
        
        Optional<Noticias> respuesta = noticiasRepositorio.findById(id);

        if (respuesta.isPresent()) {
            Noticias noticia = respuesta.get();
            
            noticia.setTitulo(titulo);
            noticia.setCuerpo(cuerpo);

            noticiasRepositorio.save(noticia);

        }
    }
    
    @Transactional(readOnly = true)
    public Noticias getOne(Long id){
        return noticiasRepositorio.getOne(id);
    }
    
    @Transactional
    public void eliminar(Long id) throws MyException{
        
        Noticias noticia = noticiasRepositorio.getById(id);
        noticia.setAlta(false);
        
        noticiasRepositorio.save(noticia);

    }
    
    private void validar(String titulo, String cuerpo) throws MyException {
        
        if (titulo.isEmpty() || titulo == null) {
            throw new MyException("el titulo no puede ser nulo o estar vacio");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MyException("el cuerpo no puede ser nulo o estar vacio");
        }
    }
}
