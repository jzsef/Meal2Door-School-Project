# Tesztjegyzőkönyv-`Email-es funkciók tesztelése és Kapcsolatfelvételi űrlap tesztelése`

Az alábbi tesztdokumentum a `2021_IB153I-8_etel` projekthez tartozó `9.3.15.  Email-es kiértesítés új rendelés esetén az adott étteremnek és vásárlónak` `Kapcsolatfelvételi űrlap biztosítása új éttermek számára` és  funkcióihoz készült. Felelőse: `Hódi Ákos` 



## 1. Teszteljárások (TP)

### 1.1. Email-es funkciók tesztelése  
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy fiókba
    1. lépés: Válasszunk ki egy tetszőleges éttermet
    2. lépés: Rakjunk bele egy (vagy több) ételt a kosárba 
    3. lépés: Nyomjuk meg az `order` gombot 
    4. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `E-mail érkezik a bejelentkezett felhasználó és azon éttermek e-mail címére, amelyektől vásároltunk`

### 1.2. Kapcsolatfelvételi űrlap tesztelése 
- Azonosító: TP-02
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, kattintsunk a `Register` gombra
    1. lépés: Töltsük ki megfelelően az üres mezőket
    2. lépés: Kattintsunk a `Register` gombra 
    4. lépés: Ellenőrizzük az eredményt. Elvárt eredmény: `Létrejött egy új felhasználó`

## 2. Teszesetek (TC)

### 2.1. Email-es funkciók tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Email-es funkciók tesztelése 
- Bemenet: Ételt kosárba rakunk
- Művelet: nyomjuk meg az `order` gombot 
- Elvárt kimenet: `E-mail érkezik a bejelentkezett felhasználó és azon éttermek e-mail címére, amelyektől vásároltunk`


### 2.2. Kapcsolatfelvételi űrlap funkció tesztesetei

#### 2.2.1. TC-01
- TP: TP-01
- Leírás: Kapcsolatfelvételi űrlap tesztelése 
- Bemenet: `Register` oldalon üres mezők kitöltése
- Művelet: nyomjuk meg az `Register` gombot 
- Elvárt kimenet: `Létrejött egy új felhasználó`



## 3. Tesztriportok (TR)

### 3.1. Email-es funkciók tesztriportjai

#### 3.1.1. TR-01 (TC-01)
- TP: TP-01
    1. lépés: A `hagi udvar` étterem oldalára lépek
    2. lépés: `Marhahúsos ramen leves` nevű elemet a kosárba rakom az `Add to Shopping Cart` gomb megnyomásával
    3. lépés: A `Kosár` oldalon rákattintok az `order` gombra
    4. lépés: Helyes eredményt kaptam (E-mail érkezik a bejelentkezett felhasználó és azon éttermek e-mail címére, amelyektől vásároltunk)    
    

### 3.2. Kapcsolatfelvételi űrlap funkció tesztriportjai

#### 3.2.1. TR-01 (TC-01)
- TP: TP-02
    1. lépés: A `Register` oldalon a `Full Name` szöveg alatti mezőbe beírok egy nevet: `Peti`
    2. lépés: Kitöltöm megfelelően a megmaradt üres mezőket
    3. lépés: `Register` gomb lenyomása után létrejön egy `Peti` nevű felhasználó
    3. lépés: Helyes eredményt kaptam (létrejött egy `Peti` nevű felhasználó)    


    