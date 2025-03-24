![image](https://github.com/user-attachments/assets/1d0b2621-408a-448d-ba9e-167679aeb4e6)
&nbsp;
&nbsp;

**Semestrálna práca z predmetu**

**Návrhové vzory**

(Dokumentácia)

&nbsp;

Meno: Dávid Mičo

Študijná skupina: 5ZIS11

Akademický rok: 2024/2025

Semester: Zimný

**Obsah**

[Popis problému 3](#_Toc185512464)

[Popis univerzálneho návrhu 4](#_Toc185512465)

[Popis mojej implementácie 5](#_Toc185512466)

[Programátorská príručka pre budúce rozšírenie 6](#_Toc185512467)

# Popis problému

V rámci mojej semestrálnej práce som sa snažil pomocou Návrhových vzorov zovšeobecniť návrh mojej semestrálnej práce z predmetu Informatika 2. Ide o hru Šach, určenú na hranie pre dvoch hráčov na tom istom počítači. Pôvodný návrh tejto semestrálnej práce vyzeral nasledovne:

![Šach_povodny_navrh_UML](https://github.com/user-attachments/assets/f9c412d8-7c2f-472b-83f7-d51838ad0b75)

Cieľom mojej práce bolo do pôvodnej aplikácie doimplementovať možnosti Undo a Redo ťahov, čo som sa snažil dosiahnuť pomocou návrhového vzoru **Command**. Možnosť Undo a Redo ťahu umožní hráčovi návrat k uskutočneným ťahom alebo v priateľskej partii opravu chyby.

Ďalej som chcel implementovať vypisovanie jednotlivých ťahov do konzoly a súboru pomocou návrhového vzoru **Observer**. Toto rozšírenie umožní po skončení alebo počas priebehu partie zapisovať jednotlivé ťahy hry do konzoly aj súboru súčasne. Hráč si bude môcť pozrieť po skončení partie uskutočnené ťahy.

Posledným cieľom bolo rozšírenie vykresľovania šachovnice na iné grafické rozhrania ako len pôvodné Java Swing plátno, tu bolo vhodné použiť návrhový vzor **Bridge**. Po implementácií tejto funkcie si používateľ bude môcť na začiatku hry vybrať konkrétne grafické rozhranie, na ktoré sa mu bude šachovnica vykresľovať.

# Popis univerzálneho návrhu

![Šach_univerzalny_navrh_UML](https://github.com/user-attachments/assets/9cc29522-e8be-4cf5-b3cb-cce683bbc9b2)

V triede Hra som použil návrhový vzor **Command**, ktorý mi uľahčil implementovanie Undo a Redo ťahov v šachu. Vďaka nemu budeme môcť uchovávať históriu vykonaných ťahov a každý pohyb figúrky bude predstavovať konkrétny Command.

Ďalej som použil návrhový vzor **Observer**, ktorý mi uľahčil notifikáciu ostatných komponentov aplikácie pri zmene stavu Hry. V mojom návrhu bude notifikovať trieda Hra triedy PohybKonzolaObserver a PohybSuborObserver o zmene stavu hry a pošle im celý posledný vykonaný ťah na zápis.

Ako posledný som použil návrhový vzor **Bridge**, ktorý mi uľahčil oddelenie abstrakcie(Hra) od konkrétnej implementácie(IRenderer). V pôvodnom návrhu bola logika figúrok a hry pevne previazaná s grafickým JavaSwing rozhraním. Pridaním návrhového vzoru Bridge sa nám podarilo oddeliť túto logiku od konkrétnej vykresľovacej technológie. Okrem JavaSwingu bolo implementované ešte vykresľovanie do konzoly.

# Popis mojej implementácie

Ako prvý som implementoval návrhový vzor **Command**. Implementoval som ho pomocou interface ICommand, ktorý bude zovšeobecnením konkrétnych Commandov, má funkcie execute a unexecute, ktoré využijeme pri implementácii Undo a Redo ťahov. Trieda PohybCommand definuje náš konkrétny Command pohybu figúrky. Pri každom pohybe figúrky v triede hra vytvoríme nový Command, ktorý uložíme do Undo stacku, v ktorom budú uložené predchádzajúce ťahy a budeme môcť z neho jednoducho prechádzať k predchádzajúcim ťahom. Do triedy Hra pribudli funkcie executeCommand, Undo a Redo a taktiež Redo stack, do ktorého budeme ukladať nasledujúce ťahy pri aktivovaní funkcie Undo. Tento Command bude tak isto v sebe uchovávať svoju String reprezentáciu, ktorá sa bude využívať pri zápise ťahov do konzoly alebo súboru.

Ďalej som implementoval návrhový vzor **Observer**. Hra vždy po vykonaní ťahu čierneho hráča notifikuje triedu PohybKonzolaObserver a PohybSuborObserver o zmene stavu hry a pošle mu dva posledné vykonané ťahy, následne tieto Observery zapíšu posledné dva ťahy do konzoly. Zapíšu tak celý jeden ťah hráčov, ako sa to štandardne zvykne. PohybKonzolaObserver a PohybSuborObserver implementuje interface IObserver, ktorý bude zovšeobenením ďalších konkrétnych Observerov. IObserver definuje metódu update, ktorá slúži na notifikáciu Observerov o zmene pozorovaného objektu a metódu onGameOver, ktorá informuje Observerov o skončení hry. Observer PohybSuborObserver využije túto informáciu na uzavretie zapisovača do súboru, predíde sa tak znehodnoteniu dát.

Ako posledný som implementoval návrhový vzor **Bridge**. Bol pridaný atribút IRenderer do triedy Hra. Používateľ si vždy na začiatku hry zvolí spôsob ako sa bude šachová partia vykresľovať. Zatiaľ si môže vybrať z vykresľovania pomocou JavaSwingu alebo Konzoly. Hra stále riadi priebeh hry, akurát nekomunikuje priamo s používateľom pomocou JOptionPane, ako to bolo v pôvodnom návrhu. Namiesto toho posiela správy inštancii IRenderer, za ktorou sa skrýva konkrétna implementácia. Tento konkrétny IRenderer potom komunikuje s používateľom a vykresľuje mu šachovnicu.

# Programátorská príručka pre budúce rozšírenie

Vďaka implementácii návrhového vzoru **Command** budeme môcť do šachovej hry pridávať ďalšie konkrétne príkazy (Commandy), ako je napríklad rošáda alebo povýšenie pešiaka. Každý nový Command musí implementovať rozhranie ICommand a pri uskutočnení daného typu ťahu sa v triede Hra vytvorí a vykoná. Tým sa príkaz uloží do zásobníka undoStack, odkiaľ ho bude možné neskôr získať na spätné pozretie alebo odvolanie (odvykonanie), čím plne využijeme možnosti návrhového vzoru Command.

V rámci implementácie návrhového vzoru **Observer** je možné pridať ďalší konkrétny observer, napríklad taký, ktorý bude zapisovať vykonané ťahy do binárneho súboru. Stačí zabezpečiť, aby nový observer implementoval rozhranie IObserver a aby sa v triede Hra pri inicializácii inštancie pridal pomocou metódy addObserver do zoznamu observerov. Takto navrhnutý systém potom automaticky zabezpečí notifikovanie nového observera o každej zmene stavu hry.

Implementovanie návrhového vzoru **Bridge** nám umožnilo pridať ďalšiu vykresľovaciu technológiu hry. Pri ďalšom budúcom rozšírení nám stačí vytvoriť nový konkrétny typ Renderera, ktorý bude implementovať interface IRenderer. Tento nový Renderer bude svojim spôsobom podľa inštancie triedy Sachovnica vykresľovať stav šachovnice. Nakoniec ešte treba pridať ďalšiu možnosť voľby Renderera, ktorá sa ukáže používateľovi po spustení aplikácie.
