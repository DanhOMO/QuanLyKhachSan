/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.bean;

import javax.swing.JLabel;

/**
 *
 * @author nguye
 */
public class DanhMucBean {
    private String kind;
    private JLabel jlb;
    public DanhMucBean(String kind, JLabel jlb){
        this.kind = kind;
        this.jlb = jlb;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }
    
}
