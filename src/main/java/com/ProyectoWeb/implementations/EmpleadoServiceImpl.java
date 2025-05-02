package com.ProyectoWeb.implementations;

import com.ProyectoWeb.entities.Empleado;
import com.ProyectoWeb.repositories.EmpleadoRepository;
import com.ProyectoWeb.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public Empleado insert(Empleado emp) {
        return empleadoRepository.save(emp);
    }

    @Override
    public Empleado getById(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado update(Long id, Empleado emp) {
        Optional<Empleado> existEmpOpt = empleadoRepository.findById(id);
        if(existEmpOpt.isPresent()){
            Empleado existEmp = existEmpOpt.get();
            existEmp.setNombre(emp.getNombre());
            existEmp.setCargo(emp.getCargo());
            existEmp.setCorreo(emp.getCorreo());
            existEmp.setTelefono(emp.getTelefono());
            existEmp.setSalario(emp.getSalario());
            return empleadoRepository.save(existEmp);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }
}
