
# SwiftRef API
Swiftref API is used to validate IBANs , get bank details and bic code details


## Overview
This API include 3 endpoints (Iban validity,bic details and Iban bic retreival)


## API Reference

#### Check IBAN validity

```http
  GET /v1/ibans/{iban}/validity
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `iban` | `String` | **Required**. |

#### IBAN bic code

```http
  GET /v1/ibans/{iban}/bic
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `iban`      | `string` | **Required**. |

#### Get Bic Details
```http
  GET /v1/bics/{bic}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `bic`      | `string` | **Required**. |


## Documentation

[IBAN Validity Documentation](https://capitaljo.atlassian.net/wiki/spaces/AI/pages/3794403351/Get+IBAN+Validity)

[Get BIC code details Documentation](https://capitaljo.atlassian.net/wiki/spaces/AI/pages/3794370573/Get+BIC+Code+Details)

[Get BIC from IBAN Documentation](https://capitaljo.atlassian.net/wiki/spaces/AI/pages/3794403402/Get+BIC+from+IBAN)


## Authors

- ##### Murad Shahin

