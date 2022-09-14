# Tesztjegyzőkönyv-Felhasználói munkamenet

Az alábbi tesztdokumentum a 2021_IB153I-8_etel projekthez tartozó 9.4.4. Felhasználói munkamenet funkcióihoz készült. Felelőse: Kiss Máté Botond 

## 1. Teszteljárások (TP)

### 1.1. Vásárlás funkció tesztelése 
- Azonosító: TP-01
- Tesztesetek: TC-01
- Leírás: Vásárlás funkció tesztelése
    0. lépés: Nyissuk meg az alkalmazást, és lépjünk be egy felhasználói fiókba
    1. lépés: Válasszunk ki egy tetszőleges éttermet
    2. lépés: Helyezzünk a kosárba tetszőleges mennyiségű ételt/italt
    3. lépés: Nyomjuk meg az Kosár gombot 
    4. lépés: Ellenőrizzük az eredményt

### 1.2. Fizetés funkció tesztelése
- Azonosító: TP-02
- Tesztesetek: TC-01
- Leírás: Fizetés funkció tesztelése
    0. lépés: Győződjünk meg róla hogy van a kosárban termék
    1. lépés: Kattintsunk rá a Megrendelés gombra
    2. lépés: Ellenőrizzük, hogy a helyes összeg lett levonva az egyenlegünkből

### 1.3 Egyenlegfeltöltés funkció tesztelése
- Azonosító: TP-03
- Tesztesetek: TC-01, TC-02
- Leírás: Egyenlegfeltöltés funkció tesztelése
    0. lépés: Lépjünk a profilunkra, majd kattintsunk rá a Fizetés gombra
    1. lépés: Adjuk meg az adatokat
    2. lépés: Kattintsunk a Fizetés gombra
    3. lépés: Ellenőrizzük, hogy a tranzakció végrehajtódott e

## 2. Teszesetek (TC)

### 2.1. Vásárlás funkció tesztesetei

#### 2.1.1. TC-01
- TP: TP-01
- Leírás: Vásárlás funkció tesztelése 
- Bemenet: Kiválasztott ételek/talok
- Művelet: nyomjuk meg az Kosár gombot 
- Elvárt kimenet: A kosárba rakott termékek a kosárban vannak

### 2.2. Fizetés funkció tesztesetei

#### 2.2.1. TC-01
- TP: TP-02
- Leírás: Fizetés funkció tesztelése
- Bemenet: A kosár tartalma
- Művelet: Nyomjuk meg a Megrendelés gombot 
- Elvárt kimenet: Sikeres vásárlás

### 2.3. Egyenlegfeltöltés funkció tesztelésee

#### 2.3.1 TC-01
- TP: TP-03
- Leírás: Egyenlegfeltöltés funkció tesztelése helyes adatokkal
- Bemenet: Helyes bankkártyaadtok
- Művelet: Adjunk meg helyes adatokat, majd nyomjunk a Fizetés gombra
- Elvárt kimenet: Sikeres egyenlegfelöltés

#### 2.3.2 TC-02
- TP: TP-03
- Leírás: Egyenlegfeltöltés funkció tesztelése hiányzó  adatokkal
- Bement: Hagyjunk ki aa bankkártyaadatok közül egy tetszőleges adatot
- Művelet: Nyumjunk a Fizetés gombra
- Elvárt kimenet: Sikertelen egyenlegfeltöltés (minden mező kitöltése kötelező)

## 3. Tesztriportok (TR)

### 3.1. Vásárlás funkció tesztriportjai

#### 3.1.1. TR-01 (TC-01)
- TP: TP-01
    0. lépés: Beléptem egy felhasználói fiókba.
    1. lépés: Kiválasztottam a Diófa Vendéglő éttermet
    2. lépés: Kiválasztottam a Raguleves csinatésztával és az Erőleves csinagtésztával ételeket
    3. lépés: Rákattintottam a Kosár gombra
    4. lépés: A kosárba rakott termékek a kosárban vannak

### 3.2. Fizetés funkció tesztriportjai

#### 3.2.1. TR-01 (TC-01)
- TP: TP-02
    0. lépés: Meggyőződtem róla, hogy a kosárban vannak termékek
    1. lépés: Rákattintottam a Megrendelés gombra
    2. lépés: Ellenőriztem, hogy a helyes összeg lett levonva az egyenlegemből

### 3.3 Egyenlegfeltöltés funkció tesztriportjai

#### 3.2.1 TR-01 (TC-01)
- TP: TP-03
    0. lépés: A profilomra léptem, majd rákattintottam a fizetés gombra
    1. lépés: Megadtam helyes bankkártyaadatokat
    2. lépés: Rákattintottam a Fizetés gombra
    3. lépés: Ellenőriztem, hogy helyes összeg lett hozzáadva az egyenlegemhez

#### 3.2.2 TR-01 (TC-02)
- TP: TP-03
  0. lépés: A profilomra léptem, majd rákattintottam a fizetés gombra
  1. lépés: Kihagytam a Név mezőből az adatot
  2. lépés: Rákattintottam a Fizetés gombra
  3. lépés: Ellenőriztem, hogy a tranzakció nem hajtódott végre