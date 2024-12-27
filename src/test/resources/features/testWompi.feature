# language: en
@FeatureWompi
Feature: consumir los apis de prueba
  yo como usuario que quiere consumir las apis de wompi
  quiero disponibilizar los enpoints
  para validar su correcta ejecucion

  Background: set environment
    Given un usuario en ambiente de pruebas "UAT"

  @TestMerchants
  Scenario: Consumir el enpoint GET merchants
    When se encuentre en la url del servicio "MERCHANTS" con su llave publica "public_key"
    Then permite la ejecucion del servicio "GET" de manera correcta

  @TestServiceTransaction
  Scenario Outline: Consumir el enpoint POST transaction
    Given un usuario con tokens validos
    When se encuentre en la url del servicio "TRANSACTIONS" con el body "bodyTransaction"
      | amount_in_cents   | currency   | customer_email   | reference   | type   | phone_number   | payment_description   | sandbox_status   | acceptance_token   | accept_personal_auth   | user_type   |
      | <amount_in_cents> | <currency> | <customer_email> | <reference> | <type> | <phone_number> | <payment_description> | <sandbox_status> | <acceptance_token> | <accept_personal_auth> | <user_type> |
    Then permite la ejecucion del servicio TRANSACTIONS de manera correcta
    Examples:
      | amount_in_cents | currency | customer_email           | reference      | type                 | phone_number | payment_description | sandbox_status       | acceptance_token | accept_personal_auth | user_type |
      | 2500000         | COP      | pepito_perez@example.com | 23422er32y314  | NEQUI                | 3107654321   | pago wompi test     | APPROVED             | acceptance_token | accept_personal_auth | PERSON    |
      | 2500000         | COP      | pepito_perez@example.com | 23225r32u4yd8  | BANCOLOMBIA_QR       | 3107654321   | pago wompi test     | APPROVED             | acceptance_token | accept_personal_auth | PERSON    |
      | 2500000         | COP      | pepito_perez@example.com | 22er1323v34td8 | PCOL                 | 3107654321   | pago wompi test     | APPROVED_ONLY_POINTS | acceptance_token | accept_personal_auth | PERSON    |
      | 2500000         | COP      | pepito_perez@example.com | 23672er3b233d8 | BANCOLOMBIA_TRANSFER | 3107654321   | pago wompi test     | APPROVED_ONLY_POINTS | acceptance_token | accept_personal_auth | PERSON    |