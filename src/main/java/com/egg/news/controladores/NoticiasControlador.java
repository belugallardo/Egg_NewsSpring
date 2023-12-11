/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.news.controladores;

import com.egg.news.excepciones.MyException;
import com.egg.news.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author belugallardo
 */
@Controller
@RequestMapping("/noticia")
public class NoticiasControlador {
    
    @Autowired
    private NoticiaServicio noticiasServicio;
    
   
    @GetMapping("/registrar")
    public String registrar(){

        return "noticia_form.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        try {
            noticiasServicio.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia fue cargada correctamente!");
                    
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_form.html";
        }
        return "index.html";
    }
    
    
    
    
}
