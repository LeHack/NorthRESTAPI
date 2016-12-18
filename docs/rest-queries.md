# Przykłady obsługiwanych zapytań REST

## CRUD

- Create (POST)
```sh
curl http://localhost:8080/NorthRESTAPI/rest/shipper --data-ascii '{"companyName": "The Shipper", "phone": "123-456-789"}' -H "Content-Type: application/json"
```
- Read (GET)
```sh
curl http://localhost:8080/NorthRESTAPI/rest/shipper/all
curl http://localhost:8080/NorthRESTAPI/rest/shipper/5
```
- Update (POST)
```sh
curl http://localhost:8080/NorthRESTAPI/rest/shipper/update --data-ascii '{"id": 5,"companyName": "The Other Shipper"}' -H "Content-Type: application/json"
```
- Delete (GET)
```sh
curl http://localhost:8080/NorthRESTAPI/rest/shipper/delete/5
```

## Wyszukiwanie po kluczu obcym (GET)

- Pokaż wszystkie zamówienia dla pracownika o Id: 5
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order/employee/5
```

- Pokaż wszystkie zamówienia dla dostawcy o Id: 2
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order/shipper/2
```

- Pokaż wszystkie zamówienia dla klienta o Id: QUICK
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order/customer/QUICK
```

## Przykładowe zarządzanie zamówieniem
- Utwórz zamówienie
```sh
export orderJSON='{
    "customerId": "QUICK",
    "shipperId": 5,
    "employeeId": 4,
    "order": [
        {
            "productId": 70,
            "quantity": 5,
            "unitPrice": 3.3
        },
        {
            "productId": 35,
            "quantity": 2,
            "unitPrice": 5.2,
            "discount": 10
        },
        {
            "productId": 15,
            "quantity": 2,
            "unitPrice": 10.2
        }
    ],
    "orderDate": "2016-10-08",
    "requiredDate": "2016-12-10",
    "shippedDate": "2016-11-05",
    "shipName": "AGH",
    "shipAddress": "aleja Adama Mickiewicza 30",
    "shipCity": "Kraków",
    "shipRegion": "Małopolska",
    "shipPostalCode": "30-059",
    "shipCountry": "Polska"
}';
curl http://localhost:8080/NorthRESTAPI/rest/order --data-ascii "$orderJSON" -H "Content-Type: application/json"
```
- Pokaż szczegóły zamówienia
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order-details/order/11078
```
- Pokaż zamówienia dla pojedynczego produktu
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order-details/product/70
```
- Usunięcie produktu z bazy
```sh
curl http://localhost:8080/NorthRESTAPI/rest/product/delete/70
```
- Pozostałe elementy zamówienia pozostają w bazie
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order-details/order/11078
```
- Usunięcie zamówienia
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order/delete/11078
```
- Usuwa również relację z produktami
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order-details/product/35
```

## Wyszukiwanie po wybranym parametrze
- Dostawcy po nazwie firmy
```sh
curl http://localhost:8080/NorthRESTAPI/rest/shipper/by/companyName/Speedy%20Express
```
- Zamówienia po kraju docelowym
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order/by/shipCountry/France
```
- Zamówienia po dacie zamówienia
```sh
curl http://localhost:8080/NorthRESTAPI/rest/order/by/orderDate/1996-07-04
```
- Klienci po mieście
```sh
curl http://localhost:8080/NorthRESTAPI/rest/customer/by/city/Warszawa
```
- Produkty, które nie są już w sprzedaży
```sh
curl http://localhost:8080/NorthRESTAPI/rest/product/by/discontinued/1
```