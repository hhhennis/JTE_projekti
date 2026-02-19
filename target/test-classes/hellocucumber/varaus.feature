Feature: Huonevaraus

Background:
  Given kayttaja on varaussivulla

Scenario Outline: uuden varauksen tekeminen
  When kayttaja valitsee "<huone>" ja varauksen "<aika>"
  And "<huone>" on "<tila>" kayttajan valitsemana aikana "<aika>", kayttaja klikkaa varauspainiketta
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | huone | aika       | tila    | tulos              |
  | A100  | 2026-01-10 | vapaa   | Varaus onnistui    |
  | A100  |            | varattu | Huone ei ole vapaa |


Scenario Outline: Varaus epaonnistuu puuttuvan valinnan takia
  When kayttaja valitsee "<huone_tai_aika>"
  And kayttajalta jaa valitsematta "<puuttuva>"
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | huone_tai_aika | puuttuva | tulos                            |
  | A100           | aika     | Valitse varausajankohta          |
  | 2026-01-10     | huone    | Valitse huone                    |
  |                | molemmat | Valitse huone ja varausajankohta |


Scenario Outline: Varauksen peruminen
    When kayttaja valitsee peruttavan varauksen "<varaus>"
    And varaus on tilassa "<tila>"
    And kayttaja klikkaa peruutuspainiketta
    Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | varaus          | tila       | tulos                                                     |
  | A100 2026-01-10 | tulossa    | Varaus peruttu onnistuneesti                              |
  | A101 2026-01-11 | tulossa    | Varaus peruttu onnistuneesti                              |
  | A102 2026-01-12 | kaynnissa  | Kaynnissa olevaa varausta ei voi perua  |
  | A103 2026-01-13 | mennyt     | Mennytta varausta ei voi perua  |
