/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorvisitasmuseo.logica;

import com.unicauca.gestorvisitasmuseo.dao.VisitaJpaController;
import com.unicauca.gestorvisitasmuseo.entities.Visita;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Sebastian V
 */
public class HandlerTable {

    private EntityManagerFactory factory;
    private VisitaJpaController jpa;

    public HandlerTable() {
        factory = Persistence.createEntityManagerFactory("GestorVisitasMuseoPU");
        jpa = new VisitaJpaController(factory);
    }

    public void ejecutarConsultaGeneral(JTable tabla) {
        
        List<Visita> resultConsulta = jpa.findVisitaEntities();
        String[] columnas = {"ID", "Visitantes", "Grupo Social", "Fecha (dd/mm/aaaa)", "Hora", "Observaciones"};
        DefaultTableModel dm = new DefaultTableModel(null, columnas);
//        TableColumnModel columnModel = tabla.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(10);
        for (int i = 0; i < resultConsulta.size(); i++) {
            String [] fila = convertirAVector(resultConsulta.get(i));
            dm.addRow(fila);
        }
                
        tabla.setModel(dm);
    }

    private String[] convertirAVector(Visita get) {
        
        String [] vec = {""+get.getId(),""+get.getNumeropersonas(),get.getTipovisitante(),get.getFechaSimple(),get.getHoraSimple(),get.getObservaciones()};
        return vec;
    }

}
