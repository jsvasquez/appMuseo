/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorvisitasmuseo.logica;

import com.unicauca.gestorvisitasmuseo.dao.VisitaJpaController;
import com.unicauca.gestorvisitasmuseo.entities.Visita;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

    public boolean ejecutarConsulta(JTable tabla, int consulta, String clave) {
        List<Visita> resultConsulta;
        switch (consulta) {
            case 1:
                resultConsulta = jpa.findVisitaEntities();
                break;
            case 2:
                clave = "%" + clave + "%";
                System.out.println("Clave:" + clave);
                resultConsulta = jpa.findVisitaObservaciones(clave);
                break;
            default:
                resultConsulta = jpa.findVisitaEntities();
                break;
        }
        String[] columnas = {"ID", "Visitantes", "Grupo Social", "Fecha (dd/mm/aaaa)", "Hora(hh:mm)", "Observaciones"};
        DefaultTableModel dm = new DefaultTableModel(null, columnas);
        if (resultConsulta.size() > 0) {

//        TableColumnModel columnModel = tabla.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(10);
            for (int i = 0; i < resultConsulta.size(); i++) {
                String[] fila = convertirAVector(resultConsulta.get(i));
                dm.addRow(fila);
            }
            tabla.setModel(dm);
            return true;
        } else {
            tabla.setModel(dm);
            return false;
        }
    }

    private String[] convertirAVector(Visita get) {

        String[] vec = {"" + get.getId(), "" + get.getNumeropersonas(), get.getTipovisitante(), get.getFechaSimple(), get.getHoraSimple(), get.getObservaciones()};
        return vec;
    }

    public boolean consultarFechas(JTable tabla, Date fInicio, Date fFin) {
        List<Visita> resultConsulta;
        System.out.println("Inicio: " + fInicio);
        System.out.println("Fin: " + fFin);

        resultConsulta = jpa.findVisitasPorFecha(fInicio, fFin);

        String[] columnas = {"ID", "Visitantes", "Grupo Social", "Fecha (dd/mm/aaaa)", "Hora(hh:mm)", "Observaciones"};
        DefaultTableModel dm = new DefaultTableModel(null, columnas);
        if (resultConsulta.size() > 0) {

//        TableColumnModel columnModel = tabla.getColumnModel();
//        columnModel.getColumn(0).setPreferredWidth(10);
            for (int i = 0; i < resultConsulta.size(); i++) {
                String[] fila = convertirAVector(resultConsulta.get(i));
                dm.addRow(fila);
            }
            tabla.setModel(dm);
            return true;
        } else {
            tabla.setModel(dm);
            return false;
        }
    }

    public List<Integer> contarVisitasFechas(Date fInicio, Date fFin) {
        List<Visita> resultConsulta;
        int niños=0,joven=0,adulto=0,adultoMayor=0;
        
        System.out.println("Inicio: " + fInicio);
        System.out.println("Fin: " + fFin);

        resultConsulta = jpa.findVisitasPorFecha(fInicio, fFin);

        //if (resultConsulta.size() > 0) {

            for (int i = 0; i < resultConsulta.size(); i++) {
                Visita aux = resultConsulta.get(i);
                String grupo = aux.getTipovisitante();
                switch (grupo) {
                    case "Niño":
                        niños+=aux.getNumeropersonas();
                        break;
                    case "Joven":
                        joven+=aux.getNumeropersonas();
                        break;
                    case "Adulto":
                        adulto+=aux.getNumeropersonas();
                        break;
                    case "Adulto Mayor":
                        adultoMayor+=aux.getNumeropersonas();
                        break;

                }
            }
            List<Integer> conteos = new ArrayList<>();
            conteos.add(niños);
            conteos.add(joven);
            conteos.add(adulto);
            conteos.add(adultoMayor);
            return conteos;
//        } else {
//            return null;
//        }

    }

}
