Feature: Huonevaraus

Background:
  Given kayttaja on varaussivulla

Scenario Outline: uuden varauksen tekeminen
  When kayttaja valitsee "<huone>" ja varauksen "<aika>"
  And "<huone>" on "<tila>" kayttaajan valitsemana aikana "<aika>", kayttaja klikkaa varauspainiketta
  Then kayttajalle naytetaan viesti "<tulos>"


Examples:
  | huone  | aika       | tila     | tulos                |
  | A101   | 10:00-11:00| vapaa    | Varaus onnistui      |
  | A101   | 10:00-11:00| varattu  | Huone ei ole vapaa   |