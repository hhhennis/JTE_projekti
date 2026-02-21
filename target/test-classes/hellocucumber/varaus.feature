Feature: Huonevaraus

Background:
  Given kayttaja on varaussivulla

Scenario Outline: uuden varauksen tekeminen eri syotteilla
  When kayttaja valitsee "<huone>" ja varauksen "<aika>"
  And kayttaja antaa varaajan tiedot "<nimi>" "<email>" "<puhelin>"
  And "<huone>" on "<tila>" kayttajan valitsemana aikana "<aika>", kayttaja klikkaa varauspainiketta
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | huone | aika       | nimi  | email          | puhelin    | tila  | tulos               |
  | A100  | 2026-01-10 | Matti | matti@test.fi  | 0401234567 | vapaa | Varaus onnistui     |
  | A100  | 2026-01-10 | Matti | matti@test.fi  | 0401234567 | varattu | Huone ei ole vapaa |
  | A101  | 2026-01-11 |       | matti@test.fi  | 0401234567 | vapaa | Lisaa varaajan nimi |
  | A102  | 2026-01-12 | Matti |                | 0401234567 | vapaa | Lisaa sahkoposti    |
  | A103  | 2026-01-13 | Matti | matti@test.fi  |            | vapaa | Lisaa puhelinnumero |
  | A104  | 2026-01-14 |       |                | 0401234567 | vapaa | Lisaa varaajan nimi |
  | A105  | 2026-01-15 |       | matti@test.fi  |            | vapaa | Lisaa varaajan nimi |
  | A106  | 2026-01-16 | Matti |                |            | vapaa | Lisaa sahkoposti    |
  | A107  | 2026-01-17 |       |                |            | vapaa | Lisaa varaajan nimi |
  |       | 2026-01-18 | Matti | matti@test.fi  | 0401234567 | vapaa | Valitse huone |
  | A108  |            | Matti | matti@test.fi  | 0401234567 | vapaa | Valitse varausajankohta |
  |       |            | Matti | matti@test.fi  | 0401234567 | vapaa | Valitse huone ja varausajankohta |

Scenario Outline: Varauksen peruminen
  When kayttaja valitsee peruttavan varauksen huoneen "<huone>" paivalle "<aika>"
  And varaus on tilassa "<tila>"
  And kayttaja klikkaa peruutuspainiketta
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | huone | aika       | tila      | tulos                                  |
  | A100  | 2026-01-10 | tulossa   | Varaus peruttu onnistuneesti           |
  | A101  | 2026-01-11 | tulossa   | Varaus peruttu onnistuneesti           |
  | A102  | 2026-01-12 | kaynnissa | Kaynnissa olevaa varausta ei voi perua |
  | A103  | 2026-01-13 | mennyt    | Mennytta varausta ei voi perua         |
  |       | 2026-01-14 | tulossa   | Valitse huone                          |
  | A104  |            | tulossa   | Valitse varausajankohta                |
  |       |            | tulossa   | Valitse huone ja varausajankohta       |