# Przykłady obsługiwanych zapytań REST

## CRUD

- Create (POST)
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper --data-ascii '{"companyName": "The Shipper", "phone": "123-456-789"}' -H "Content-Type: application/json"
```
- Read (GET)
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper/all
curl http://localhost:8080/NorthRESTAPI/rest/shipper/5
```
- Update (POST)
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper/update --data-ascii '{"id": 5,"companyName": "The Other Shipper"}' -H "Content-Type: application/json"
```
- Delete (GET)
```
curl http://localhost:8080/NorthRESTAPI/rest/shipper/delete/5
```

## Wyszukiwanie po kluczu obcym (GET)

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

## Stworzenie nowego zamówienia (POST)
```
curl http://localhost:8080/NorthRESTAPI/rest/order --data-ascii '' -H "Content-Type: application/json"
```
