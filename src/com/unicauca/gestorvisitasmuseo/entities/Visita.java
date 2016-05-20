/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicauca.gestorvisitasmuseo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sebastian V
 */
@Entity
@Table(name = "visita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visita.findAll", query = "SELECT v FROM Visita v"),
    @NamedQuery(name = "Visita.findById", query = "SELECT v FROM Visita v WHERE v.id = :id"),
    @NamedQuery(name = "Visita.findByFecha", query = "SELECT v FROM Visita v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Visita.findByHora", query = "SELECT v FROM Visita v WHERE v.hora = :hora"),
    @NamedQuery(name = "Visita.findByTipovisitante", query = "SELECT v FROM Visita v WHERE v.tipovisitante = :tipovisitante"),
    @NamedQuery(name = "Visita.findByNumeropersonas", query = "SELECT v FROM Visita v WHERE v.numeropersonas = :numeropersonas"),
    @NamedQuery(name = "Visita.findByObservaciones", query = "SELECT v FROM Visita v WHERE v.observaciones = :observaciones")})
public class Visita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FECHA")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "HORA")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "TIPOVISITANTE")
    private String tipovisitante;
    @Basic(optional = false)
    @Column(name = "NUMEROPERSONAS")
    private int numeropersonas;
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public Visita() {
        System.out.println("sdfd");
        
        
    }

    public Visita(Long id) {
        this.id = id;
    }

    public Visita(Long id, Date fecha, String tipovisitante, int numeropersonas) {
        this.id = id;
        this.fecha = fecha;
        this.tipovisitante = tipovisitante;
        this.numeropersonas = numeropersonas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getTipovisitante() {
        return tipovisitante;
    }

    public void setTipovisitante(String tipovisitante) {
        this.tipovisitante = tipovisitante;
    }

    public int getNumeropersonas() {
        return numeropersonas;
    }

    public void setNumeropersonas(int numeropersonas) {
        this.numeropersonas = numeropersonas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unicauca.gestorgisitasmuseo.entities.Visita[ id=" + id + " ]";
    }
    
}
