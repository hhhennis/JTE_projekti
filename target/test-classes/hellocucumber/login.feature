Feature: Sisaankirjautuminen

Background:
  Given kayttaja on kirjautumissivulla

Scenario Outline: Kirjautuminen kayttajatunnuksella ja salasanalla
  When kayttaja kirjoittaa "<kayttajanimi>" ja "<salasana>"
  And kayttaja klikkaa kirjautumispainiketta
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | kayttajanimi | salasana   | tulos                                 |
  | kayttaja     | salasana   | Tervetuloa, kayttaja!                 |
  |              | salasana   | Virheellinen kayttajanimi tai salasana|
  | kayttaja     |            | Virheellinen kayttajanimi tai salasana|
  | kayt         | salasana   | Virheellinen kayttajanimi tai salasana|
  | kayttaja     | salsa       | Virheellinen kayttajanimi tai salasana|
  | kayttaja     | sssalasana | Virheellinen kayttajanimi tai salasana|
  | kkkkayttaja  | salasana   | Virheellinen kayttajanimi tai salasana|
  | ayttajaa     | alasanas   | Virheellinen kayttajanimi tai salasana|
  |              |            | Virheellinen kayttajanimi tai salasana|
  | yttis        |            | Virheellinen kayttajanimi tai salasana|
  |              | ananas     | Virheellinen kayttajanimi tai salasana|