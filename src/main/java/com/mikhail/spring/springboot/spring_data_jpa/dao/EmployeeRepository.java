package com.mikhail.spring.springboot.spring_data_jpa.dao;

import com.mikhail.spring.springboot.spring_data_jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<
        Employee, //С каким объектом будет работать репозиторий
        Integer // тип данных у поля primary key класса Employee
        > {

    public List<Employee> findAllByName(String name);

    public List<Employee> findAllByNameAndSalaryIsBetween(String name, Integer minCount, Integer maxCount);

    /**
     * Возможность использовать свое название поля в методе, не прибегая к соблюдению названия поля в БД
     * В данном случае Nam вместо Name
     * Для метода findAllByNam прописан кастомный запрос в сущности Employee через тег @NamedQuery
     */
    public List<Employee> findAllByNam(@Param(value = "myparam1") String name);

    /**
     * Возможность использовать свое название метода и тут же указать удобно запрос,
     * в который должны быть подставлены именованные параметры
     */
    @Query("from Employee e where e.name = :myparam2")
    public List<Employee> findMeDataByName(@Param("myparam2") String name);
}
