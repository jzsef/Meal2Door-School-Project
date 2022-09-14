# Tesztjegyzőkönyv-`Éttermek kezelése`

Az alábbi tesztdokumentum a `2021_IB153I-8_etel` projekthez tartozó `9.4.5. Éttermek kezelése` funkcióihoz készült. Felelőse: `Mándoki József` 



## 1. Teszteljárások (TP)

### 1.1. Admin felhasználó: étterem szerkesztés funkció tesztelése 
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy admin fiókba
    1. lépés: Válasszunk ki egy tetszőleges éttermet
    2. lépés: Változtassuk meg az étterem nevét `Bolt2`-re 
    3. lépés: Nyomjuk meg az `Modify` gombot 
    4. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Az étterem neve Bolt3 jelenik meg`

### 1.2. Étterem felhasználó: étterem szerkesztés funkció tesztelése
- Azonosító: TP-02
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy étterem fiókba
    1. lépés: Menjünk be a profilunkba
    2. lépés: Változtassuk meg az étterem nevét `Bolt3`-ra 
    3. lépés: Nyomjuk meg az `Modify` gombot 
    4. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Az étterem neve Bolt3 jelenik meg`

## 2. Teszesetek (TC)

### 2.1. Admin felhasználó: étterem szerkesztés funkció tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Admin felhasználó: étterem szerkesztés funkció tesztelése 
- Bemenet: Étterem név: `Bolt2`
- Művelet: nyomjuk meg az `Modify` gombot 
- Elvárt kimenet: `Az étterem neve Bolt2 jelenik meg`


### 2.2. Étterem felhasználó: étterem szerkesztés funkció tesztesetei

#### 2.2.1. TC-01
- TP: TP-01
- Leírás: Admin felhasználó: étterem szerkesztés funkció tesztelése 
- Bemenet: Étterem név: `Bolt3`
- Művelet: nyomjuk meg az `Modify` gombot 
- Elvárt kimenet: `Az étterem neve Bolt3 jelenik meg`



## 3. Tesztriportok (TR)

### 3.1. Admin felhasználó: étterem szerkesztés funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)
- TP: TP-01
    0. lépés: Beléptem az Admin fiókba
    1. lépés: Kiválasztottam egy éttermet
    2. lépés: Átírtam az étterem nevét Bolt2-re
    3. lépés: Megnyomtam a Modify gombot
    4. lépés: A bolt neve sikeresen megváltozott
    

### 3.2. Étterem felhasználó: étterem szerkesztés funkció tesztriportjai

#### 3.2.1. TR-01 (TC-01)
- TP: TP-01
    0. lépés: Beléptem egy Bolt fiókba
    1. lépés: Rá kattintottam a profilomra
    2. lépés: Átírtam az étterem nevét Bolt3-ra
    3. lépés: Megnyomtam a Modify gombot
    4. lépés: A bolt neve sikeresen megváltozott

    


    