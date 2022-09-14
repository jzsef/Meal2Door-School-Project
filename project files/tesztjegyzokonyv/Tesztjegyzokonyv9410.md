# Tesztjegyzőkönyv-`Éttermek kezelése`

Az alábbi tesztdokumentum a `2021_IB153I-8_etel` projekthez tartozó `9.4.10. Biztonsagi mentes` funkcióihoz készült. Felelőse: `Bertalan Krisztian` 



## 1. Teszteljárások (TP)

### 1.1. Admin felhasználó: biztonsagi mentes funkcio tesztelese
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Biztonsagi mentes funkcio tesztelese
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy admin fiókba
    1. lépés: Lepjunk ra az admin feluletre
    2. lépés: Nyomjuk meg az `Save All` gombot
    3. lépés:   Ellenőrizzük az eredményt. Elvárt eredmény: `Elmentodik`



## 2. Teszesetek (TC)

### 2.1. Admin felhasználó: biztonsagi mentes funkcio tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Admin felhasználó: biztonsagi mentes funkció tesztelése 
- Bemenet: Admin név: `root`
- Művelet: nyomjuk meg az `Save All` gombot 
- Elvárt kimenet: `Az sql egy fajlba elmentodik`





## 3. Tesztriportok (TR)

### 3.1. Admin felhasználó: biztonsagi mentes funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)
- TP: TP-01
    0. lépés: Beléptem az Admin fiókba
    1. lépés: Atleptem az admin feluletre
    2. lépés: Megnyomtam a Save All gombot
    3. lépés: Elmentodott

    



    


    