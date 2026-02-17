Feature: Huonevaraus

Background:
  Given kayttaja on varaussivulla

Scenario Outline: uuden varauksen tekeminen
  When kayttaja valitsee "<huone>" ja varauksen "<aika>"
  And "<huone>" on "<tila>" kayttajan valitsemana aikana "<aika>", kayttaja klikkaa varauspainiketta
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | huone | aika        | tila    | tulos              |
  | A101  | 10:00-11:00 | vapaa   | Varaus onnistui    |
  | A101  | 10:00-11:00 | varattu | Huone ei ole vapaa |


Scenario Outline: Varaus epaonnistuu puuttuvan valinnan takia
  When kayttaja valitsee "<huone_tai_aika>"
  And kayttajalta jaa valitsematta "<puuttuva>"
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | huone_tai_aika | puuttuva | tulos                           |
  | A101           | aika     | Valitse varausajankohta         |
  | 10:00c11:00    | huone    | Valitse huone                   |
  |                | molemmat | Valitse huone ja varausajankohta|


Scenario Outline: Varauksen peruminen
    When kayttaja valitsee peruttavan varauksen "<varaus>"
    And kayttaja klikkaa peruutuspainiketta
    Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | varaus                           | tulos                                                     |
  | Kokoushuone A 09:00-10:00        | Varaus peruttu onnistuneesti                              |
  | Kokoushuone B 11:00-12:00        | Varaus peruttu onnistuneesti                              |
  | Auditorio 13:00-14:00 (jo alkanut)| Menneisyyden tai kaynnissa olevaa varausta ei voi perua  |
  | Kokoushuone C 15:00-16:00        | Varaus peruttu onnistuneesti                              |
