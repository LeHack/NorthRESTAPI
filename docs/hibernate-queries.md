# Zapytania


W hibernate obiekt może przebywać w jednym z 4 stanów:
- __transient__ - oznacza obiekt który został utworzony ale nie jest w żaden sposób powiązany z sesją hibernate, zmiany
w takim obiekcie nie zostaną zapisane do bazy, nie są monitorowane przez sesję
- __persistent__ - oznacza obiekt który jest monitorowany przez sesję, zmiana w takim obiekcie jest monitorowana przez
sesję i zapisana do bazy (nawet jeśli nie wywołamy bezpośrednio np. metody save())
- __detached__ - oznacza obiekt który został odłączony od sesji, nie jest już powiązany z bazą danych, obiekt przechodzi
w stan detached poprzez zamknięcie sesji lub wyczyszczenie cache'u sesji
- __removed__ - obiekt został usunięty
Poniższy obrazek ilustruje możliwe stany obiektów w hibernate:

![Hibernate architecture Text](http://jaiswaltraining.com/hibernate/images/img8.png)


Przykładowe interfejsy które możemy stosować w tworzeniu zapytań do bazy:
- __Query__
- __NamedQuery__ / __NamedNativeQuery__
- __Criteria__ (oraz __DetachedCriteria__ do tworzenia zapytań bez konieczności posiadania sesji)

Przykłady zastosowania w projekcie:
__NamedQuery__:
```xml
    <sql-query name="findShipperById">
        <return alias="Shipper" class="Shipper"/>
        <![CDATA[
            select * from shippers s
            where s.shipperid = :shipperId
        ]]>
    </sql-query>
```
