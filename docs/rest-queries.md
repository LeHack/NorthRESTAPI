# Przykłady obsługiwanych zapytań REST

## CRUD

- Create
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper --data-ascii '{"companyName": "The Shipper", "phone": "123-456-789"}' -H "Content-Type: application/json"
```
- Read
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper/all
curl http://localhost:8080/NorthRESTAPI/rest/shipper/5
```
- Update (single and/or multi field)
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper/update --data-ascii '{"id": 5,"companyName": "The Other Shipper"}' -H "Content-Type: application/json"
```
- Delete
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper/delete/5
```

## Proste raporty

- Pokaż wszystkie zamówienia dla pracownika o Id: 5
```
curl http://localhost:8080/NorthRESTAPI/rest/order/employee/5
```

- Pokaż wszystkie zamówienia dla dostawcy o Id: 2
```
curl http://localhost:8080/NorthRESTAPI/rest/order/shipper/2
```

- Pokaż wszystkie zamówienia dla klienta o Id: QUICK
```
curl http://localhost:8080/NorthRESTAPI/rest/order/customer/QUICK
```
