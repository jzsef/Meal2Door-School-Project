# Tesztjegyzőkönyv-`Ételek kezelése`

Az alábbi tesztdokumentum a `2021_IB153I-8_etel` projekthez tartozó `9.4.6. Ételek kezelése` funkcióihoz készült. Felelőse: `Tolnai József` 



## 1. Teszteljárások (TP)

### 1.1. Admin felhasználó: Ételek szerkesztése funkció tesztelése 
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy admin fiókba
    1. lépés: Válasszunk ki egy tetszőleges éttermet
    2. lépés: Válasszunk ki egy tetszőleges ételt
    3. lépés: Változtassuk meg az étel árát `420` Forintra 
    4. lépés: Nyomjuk meg az `Mentés` gombot 
    5. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Az étel ára megváltozik 420 forintra`

### 1.2. Étterem felhasználó: Ételek szerkesztése funkció tesztelése
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy admin fiókba
    1. lépés: Válasszunk ki egy tetszőleges éttermet
    2. lépés: Válasszunk ki egy tetszőleges ételt
    3. lépés: Változtassuk meg az étel árát `420` Forintra 
    4. lépés: Nyomjuk meg az `Mentés` gombot 
    5. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Az étel ára megváltozik 420 forintra`

## 2. Teszesetek (TC)

### 2.1. Admin felhasználó: Ételek szerkesztése funkció tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Admin felhasználó: Ételek szerkesztése funkció tesztelése 
- Bemenet: Étel ár: `420`
- Művelet: nyomjuk meg az `Mentés` gombot 
- Elvárt kimenet: `Az étel ára megváltozik 420 forintra`


### 2.2. Étterem felhasználó: Ételek szerkesztése funkció tesztesetei

#### 2.2.1. TC-01
- TP: TP-01
- Leírás: Étterem felhasználó: Ételek szerkesztése funkció tesztelése 
- Bemenet: Étel ár: `420`
- Művelet: nyomjuk meg az `Mentés` gombot 
- Elvárt kimenet: `Az étel ára megváltozik 420 forintra`



## 3. Tesztriportok (TR)

### 3.1. Admin felhasználó: Ételek szerkesztése funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)
- TP: TP-01
    
    

### 3.2. Étterem felhasználó: Ételek szerkesztése funkció tesztriportjai

#### 3.2.1. TR-01 (TC-01)
- TP: TP-01
    


    