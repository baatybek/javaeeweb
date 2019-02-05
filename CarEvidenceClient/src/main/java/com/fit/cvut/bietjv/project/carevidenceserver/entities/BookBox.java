/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fit.cvut.bietjv.project.carevidenceserver.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author beksultan
 */
public class BookBox {
    private List <Book> books = new ArrayList();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    
}
