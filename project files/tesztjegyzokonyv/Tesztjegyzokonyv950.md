# Tesztjegyzőkönyv-`Profil adatok módosítása és Kijelentkezés funkció`

Az alábbi tesztdokumentum a `2021_IB153I-8_etel` projekthez készült. Felelőse: `Hódi Ákos` 

## 1. Teszteljárások (TP)

### 1.1. Profil adatok módosításának tesztelése 
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Profil adatok módosításának tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy felhasználói fiókba
    1. lépés: Kattintsunk a Profile fülre
    2. lépés: A `New Phone Number:` felirat melleti mezőbe írjunk egy új számot
    3. lépés: Nyomjuk meg a mező alatt levő `Modify` gombot 
    4. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `A telefonszám megváltozik az új számra, amit beírtunk`

### 1.2. Kijelentkezés funkció tesztelése
- Azonosító: TP-02
- Tesztesetek: TC-01
- Leírás: Fizetés funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy felhasználói fiókba
    1. lépés: Kattintsunk a `Logout` fülre
    2. lépés: A `Logout` oldalra lépés után kattintsunk rá a `Log Out` gombra
    3. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Kijelentkeztünk`

## 2. Teszesetek (TC)

### 2.1. Profil adatok módosításának tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Profil adatok módosításának tesztelése 
- Bemenet: A `New Phone Number:` felirat melleti mezőbe egy új szám beírása
- Művelet: nyomjuk meg az `Modify` gombot 
- Elvárt kimenet: `A telefonszám megváltozik az új számra amit beírtunk`


### 2.2. Kijelentkezés funkció tesztesetei

#### 2.2.1. TC-01
- TP: TP-02
- Leírás: Kijelentkezés tesztelése 
- Bemenet: Nincs
- Művelet: Rákattintunk a `Log Out` gombra 
- Elvárt kimenet: `Kijelentkeztünk``


## 3. Tesztriportok (TR)

### 3.1. Profil adatok módosításának tesztriportjai

#### 3.1.1. TR-01 (TC-01)
- TP: TP-01
    1. lépés: A `New Phone Number:` felirat melleti mezőbe beírtam egy 3-ast
    2. lépés: `Modify` gomb lenyomása után megváltozik a telefonszám 3-ra
    3. lépés: Helyes eredményt kaptam (a telefonszám 3 lesz)

### 3.2. Négyzetre emelés funkció tesztriportjai

#### 3.2.1. TR-01 (TC-01)
- TP: TP-02
    1. lépés: A `Logout` fülre kattintok
    2. lépés: Miután bedobott a Logout oldalra kattintsunk a `Log out` gombra
    3. lépés: Helyes eredményt kaptam (kijelentkezett)


    