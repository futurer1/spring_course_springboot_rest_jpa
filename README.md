# spring_course_springboot_rest_jpa

Ключевое место использования JpaRepository (org.springframework.data.jpa.repository.JpaRepository)
- src/main/java/com/mikhail/spring/springboot/spring_data_jpa/dao/EmployeeRepository.java

```java
public interface EmployeeRepository extends JpaRepository<
        Employee, //С каким объектом будет работать репозиторий
        Integer // тип данных у поля primary key класса Employee
        > {

    public List<Employee> findAllByName(String name);

    // поиск (find) всех (All) по имени (Name) и (And) с указанным диапазоном (Between) зарплаты (Salary)
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
```

- src/main/java/com/mikhail/spring/springboot/spring_data_jpa/entity/Employee.java

```java
@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAllByNam",
        // возможен вариант, когда параметры указываются в порядке следования
        /* query = "from Employee e where e.name = ?1" */

        // или вариант с указанием названия алиаса параметра.
        // Значения передаются из EmployeeRepository
        query = "from Employee e where e.name = :myparam1"
)
public class Employee {
   //...
}
```

- src/main/java/com/mikhail/spring/springboot/spring_data_jpa/controller/MyRESTController.java

```java
@GetMapping("/employees/name/{name}")
public List<Employee> showAllByName(@PathVariable String name) {

    return employeeService.findAllByName(name);
}

// маппинг для URL вида http://localhost:8080/api/employees/name/1/Oleg?min=100&max=990
@GetMapping("/employees/name/1/{name}")
public List<Employee> showAllByNameAndSalaryBetween(
        @PathVariable String name,
        @RequestParam(value = "min") Integer min, // ?min=100
        @RequestParam(value = "max") Integer max // &max=990
) {
    return employeeService.findAllByNameAndSalaryIsBetween(name, min, max);
}

@GetMapping("/employees/name/2/{name}")
public List<Employee> findAllByNam(@PathVariable String name) {
    return employeeService.findAllByNam(name);
}

@GetMapping("/employees/name/3/{name}")
public List<Employee> findMeDataByName(@PathVariable String name) {
    return employeeService.findMeDataByName(name);
}
```
