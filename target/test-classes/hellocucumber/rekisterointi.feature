Feature: Rekisteroityminen

Background:
  Given kayttaja on rekisteroitymissivulla

Scenario Outline: Rekisteroityminen
  When kayttaja syottaa "<nimi>", "<osoite>", "<email>" ja "<puhelinnumero>"
  And kayttaja painaa rekisterointipainiketta
  Then kayttajalle naytetaan viesti "<tulos>"

Examples:
  | nimi              | osoite         | email             | puhelinnumero        | tulos                      |
  | Matti Meikalainen | Testikatu 1    | matti@testi.fi    | 0401234567           | Rekisteroityminen onnistui |
  | Liisa Esimerkki   | Esimerkkitie 5 | liisa@testi.fi    |                      | Tayta puhelinnumero        |
  | Kari Koodari      | Koodipolku 3   | karik@example.com | 0507654321           | Rekisteroityminen onnistui |
  | Minna Malli       | Mallitie 10    | vaara-sahkoposti  | 0415552222           | Sahkopostiosoite ei kelpaa |
  | Testi Testaaja    | Testikatu 8    | testi@testi.fi    | LiianPitkaNumero9999 | Puhelinnumero ei kelpaa    |
  | Uusi Kayttaja     | Katu 9         | uusi@testi.fi     |                      | Tayta puhelinnumero        |

Scenario Outline: Rekisterointi epäonnistuu - yhteystieto on jo olemassa
  Given "<muuttuja>" arvolla "<arvo>" on jo olemassa
  When kayttaja syottaa "testi nimi", "testi osoite", "<email>" ja "<puhelinnumero>"
  And kayttaja painaa rekisterointipainiketta
  Then kayttajalle naytetaan virheviesti "<tulos>"

Examples:
  | muuttuja      | arvo           | email               | puhelinnumero | tulos                            |
  | email         | matti@testi.fi | matti@testi.fi      | 0451234567    | Sahkopostiosoite on jo kaytossa  |
  | puhelinnumero | 0507654321     | sahkoposti@posti.fi | 0507654321    | Puhelinnumero on jo kaytossa     |