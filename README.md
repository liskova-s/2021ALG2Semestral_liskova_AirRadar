# 2021ALG2Semestral_liskova_AirRadar
2021 ALG2 LS Semestral project

### Zadání
Zapište program poskytující informace o vzdušném prostoru nad letištěm Václava Havla v Praze.\
Pro letiště je veden seznam obsahující letadla plánovaných příletů a odletů, který program umožňuje manuálně aktualizovat. Aktuální informace o odletech a příletech program získává z webu typu skyscanner, případně stránek letiště. Pro každé letadlo je veden kód letu, směr (odlet/přílet), čas příletu, čas odletu, destinace, kód letiště v destinaci, poskytovatel (aerolinky), typ letadla a kód příslušného typu stroje.
V základním výstupu poskytuje program seznam letadel nacházejících se ve vzdušném prostoru nad letištěm, včetně všech dostupných informací.
Program umožňuje získání přehledu plánovaných příletů a odletů, jejich řazení dle času a abecedně dle destinace a filtrování vnitroevropských a vnitrostátních příletů a odletů.
Program dále nabízí výpis typů letadel plánovaných příletů a odletů a jejich počty. Veškeré výpisy je umožněno uložit do textového nebo binárního souboru.\
Pro komunikaci s uživatelem použijte rozhraní příkazové řádky s využitím menu.

### Návrh řešení
#### Funkční specifikace


1. Poskytnutí informací o sledovaném sektoru  
__1.1 **Aktualizace leteckého listu**  
__1.2 **Výpis informací na obrazovku**   
____1.2.1 **Výpis nástěnky** - přehled letadel a jejich typů aktuálně se nacházejících nad letištěm v Praze, aktuální informace o letišti   
____1.2.2 **Výpis plánovaných příletů**  
______1.2.2.1 Výpis plánovaných příletů řazený dle času příletu  
______1.2.2.2 Výpis plánovaných příletů řazený abecedně dle původní destinace  
______1.2.2.3 Výpis plánovaných vnitroevropských příletů řazený dle času příletu  
______1.2.2.4 Výpis plánovaných vnitroevropských příletů řazený abecedně dle původní destinace  
______1.2.2.5 Výpis plánovaných vnitrostátních příletů řazený dle času příletu    
______1.2.2.6 Výpis plánovaných vnitrostátních příletů řazený abecedně dle původní destinace    
____1.2.3 **Výpis plánovaných odletů**  
______1.2.3.1 Výpis plánovaných odletů řazený dle času příletu  
______1.2.3.2 Výpis plánovaných podletů řazený abecedně dle cílové destinace  
______1.2.3.3 Výpis plánovaných vnitroevropských odletů řazený dle času příletu  
______1.2.3.4 Výpis plánovaných vnitroevropských odletů řazený abecedně dle cílové destinace    
______1.2.3.5 Výpis plánovaných vnitrostátních odletů řazený dle času příletu    
______1.2.3.6 Výpis plánovaných vnitrostátních odletů řazený abecedně dle cílové destinace    
____1.2.4 **Výpis typů letadel** plánovaných spojů a jejich počtu  
____1.2.5 **Výpis nápovědy k programu**  
2. **Uložení informací do souboru**  
__2.1 **Uložení do binárního souboru**   
____2.1.1 Uložení výpisu nástěnky  
____2.1.2 Uložení kompletního výpisu všech dostupných informací  
____2.1.3 Uložení nápovědy  
____2.1.4 Uložení plánovaných příletů    
______2.1.4.1 Uložení plánovaných příletů řazených dle času příletu  
______2.1.4.2 Uložení plánovaných příletů řazených abecedně dle původní destinace  
______2.1.4.3 Uložení plánovaných vnitroevropských příletů řazených dle času příletu  
______2.1.4.4 Uložení plánovaných vnitroevropských příletů řazených abecedně dle původní destinace  
______2.1.4.5 Uložení plánovaných vnitrostátních příletů řazených dle času příletu    
______2.1.4.6 Uložení plánovaných vnitrostátních příletů řazených abecedně dle původní destinace    
____2.1.5 Uložení plánovaných odletů    
______2.1.5.1 Uložení plánovaných odletů řazených dle času odletu  
______2.1.5.2 Uložení plánovaných odletů řazených abecedně dle původní destinace  
______2.1.5.3 Uložení plánovaných vnitroevropských odletů řazených dle času odletu  
______2.1.5.4 Uložení plánovaných vnitroevropských odletů řazených abecedně dle původní destinace  
______2.1.5.5 Uložení plánovaných vnitrostátních odletů řazených dle času odletu    
______2.1.5.6 Uložení plánovaných vnitrostátních odletů řazených abecedně dle původní destinace   
__2.2 **Uložení do textového souboru**  
____2.2.1 Uložení výpisu nástěnky    
____2.2.2 Uložení kompletního výpisu všech dostupných informací  
____2.2.3 Uložení nápovědy  
____2.2.4 Uložení plánovaných příletů    
______2.2.4.1 Uložení plánovaných příletů řazených dle času příletu  
______2.2.4.2 Uložení plánovaných příletů řazených abecedně dle původní destinace  
______2.2.4.3 Uložení plánovaných vnitroevropských příletů řazených dle času příletu  
______2.2.4.4 Uložení plánovaných vnitroevropských příletů řazených abecedně dle původní destinace  
______2.2.4.5 Uložení plánovaných vnitrostátních příletů řazených dle času příletu    
______2.2.4.6 Uložení plánovaných vnitrostátních příletů řazených abecedně dle původní destinace  
____2.2.5 Uložení plánovaných odletů    
______2.2.5.1 Uložení plánovaných odletů řazených dle času odletu  
______2.2.5.2 Uložení plánovaných odletů řazených abecedně dle původní destinace  
______2.2.5.3 Uložení plánovaných vnitroevropských odletů řazených dle času odletu  
______2.2.5.4 Uložení plánovaných vnitroevropských odletů řazených abecedně dle původní destinace  
______2.2.5.5 Uložení plánovaných vnitrostátních odletů řazených dle času odletu    
______2.2.5.6 Uložení plánovaných vnitrostátních odletů řazených abecedně dle původní destinace   
       
    
#### Struktura vstupních a výstupních souborů
Program získává aktuální informace z webu ve formě html souboru. Parsing je  nastaven pro konkrétní stránku https://flightaware.com/live/airport/LKPR.
Pevné zdrojové soubory obsahující IATA kódy letišť jsou ve formátu .csv.

Výstupními soubory jsou textové .txt nebo binární .bin soubory. Všechny výstupní soubory obsahují hlavičku s časem poslední aktualizace dat, příslušným časovým pásmem a datem.  
V textových souborech je hlavička na prvním řádku, dále následuje uživatelem zvolený výpis.  
Binární soubory jsou ve formátu: (long - čas poslední aktualizace v sekundách od počátku dne), (UTF - časové pásmo), (UTF - datum poslední aktualizace), (UTF - požadovaný výpis).  
                            

#### Objektový návrh - diagram
![AirRadar](AirRadar_diagram.png)
[Diagram link](https://drive.google.com/file/d/11JvClS7YhgJ69gYN4qYAK9krerZML83f/view?usp=sharing)
  
### Testování
Testování bylo prováděno na datech z umělého souboru TestData.csv pro fixní čas 19:18. Testování je nutné spouštět z testovací main metody. Testování nepokrývá funkce třídy Scraper a příkaz -r (refresh), který je na fungování třídy vázán. Testování těchto funkcí je možné provést porovnáním se zdrojovými tabulkami EN ROUTE/SCHEDULED TO PRG a SCHEDULED DEPARTURES na [flightaware.com](https://flightaware.com/live/airport/LKPR).

       All test runs operated by testing main method with fixed dataset TestData.csv and fixed time 19:18.
       Dashboard appears as first output of each run automatically, therefore this output is not mentioned in tests 1-11.
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 0 (DASHBOARD - CURRENT AIRWINDOW + CURRENT AIRWINDOW AIRCRAFT TYPES)
       TEST 1 EXPECTED OUTPUT:

       Vaclav Havel Airport Prague    PRG   LKPR                                                 19:18     CEST     2021-05-26 
       _______________________________________________________________________________________________________________________
                                                           SECTOR RADAR

                Current Air window
                A     DFC4DM                    Aeropartner                                        Warsaw Frederic Chopin     WAW   18:18    CEST   19:25    CEST
                A     ECC101                Eclair Aviation               Prague                       VáclavHavelAirport     PRG   18:11    CEST   19:27    CEST
                D    NTF164G              OK Aviation Group       Czech Republic             Ostrava Leos Janacek Airport     OSR   19:10    CEST                


                _____________________________________________________________________
                   Type code                                      Aircraft type   Num
                        PC12                    Pilatus PC-12(single-turboprop)     1
                     Unknown                                            Unknown     1
                        C510                              CessnaCitationMustang     1


       -h for help
       _______________________________________________________________________________________________________________________
       source: https://flightaware.com/live/airport/LKPR                                            last refresh: 19:18


       TEST 1 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 1 (ARRIVALS)
       USER INPUT: -arrivals
       TEST 1 EXPECTED OUTPUT:

                                                               ARRIVALS
       ______________________________________________________________________________________________________________________________________________
            Flight                       Provider                                                       Airport         Departure             Arrival            

       A    KLM1359                            KLM          Netherlands                        AmsterdamSchiphol     AMS   21:06    CEST   22:18    CEST
       A    VLG996K               Vueling Airlines                Spain                          Barcelona Int'l     BCN   07:16    CEST   09:08    CEST
       A     UPS295          United Parcel Service                                 Budapest Ferenc Liszt Airport     BUD   21:11    CEST   21:55    CEST
       A    QTR44NJ                  Qatar Airways                 Doha            Budapest Ferenc Liszt Airport     BUD   17:13    CEST   17:58    CEST
       A     ABR2RG                   ASL Airlines               Dublin                 Charles de Gaulle/Roissy     CDG   04:30    CEST   06:29    CEST
       A     CSA509                 Czech Airlines        CzechRepublic                               Copenhagen     CPH   20:03    CEST   21:05    CEST
       A    CLH1392             Lufthansa Cityline                                               Frankfurt Int'l     FRA   08:51    CEST   09:40    CEST
       A    PGA1246                     Portugalia                              General Humberto Delgado Airport     LIS   14:40    WEST   18:51    CEST
       A     LZB301                   Bulgaria Air             Bulgaria                            Sofia Airport    LBSF   10:13    EEST   10:42    CEST
       A     ECC203                Eclair Aviation               Prague                                  Vnukovo     VKO   20:57     MSK   22:15    CEST
       A     ECC101                Eclair Aviation               Prague                       VáclavHavelAirport     PRG   18:11    CEST   19:27    CEST
       A     DFC4DM                    Aeropartner                                        Warsaw Frederic Chopin     WAW   18:18    CEST   19:25    CEST
       A     SCR187                        Jetstar                                                        Zurich     ZRH   09:35    CEST   10:26    CEST

       TEST 1 STATUS: PASSED

       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 2 (ARRIVALS FROM CZ BY DESTINATION)
       USER INPUT: -arrivals -d -CZ
       TEST 2 EXPECTED OUTPUT:
                                                               ARRIVALS
       ______________________________________________________________________________________________________________________________________________
            Flight                       Provider                                                       Airport         Departure             Arrival            

       A     ECC101                Eclair Aviation               Prague                       VáclavHavelAirport     PRG   18:11    CEST   19:27    CEST

       TEST 2 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 3 (ARRIVALS INVALID ARTIFICIAL COMMANDS)
       USER INPUT: -arrivals -airtypesf nhj
       TEST 3 EXPECTED OUTPUT:

       Invalid command combination.

       TEST 3 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 4 (DEPARTURES TO EU BY TIME)
       USER INPUT: -departures -t -EU
       TEST 4 EXPECTED OUTPUT:

                              DEPARTURES
       __________________________________________________________________________________________________________________________
            Flight                       Provider                                                       Airport         Departure                                

       D    NTF164G              OK Aviation Group       Czech Republic             Ostrava Leos Janacek Airport     OSR   19:10    CEST                
       D    VLG8653               Vueling Airlines                Spain                          Barcelona Int'l     BCN   10:29    EEST                
       D    CLH1393             Lufthansa Cityline                                               Frankfurt Int'l     FRA   10:32    CEST   

       TEST 4 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 5 (AIRFCRAFT TYPES IN CURRENT TIME WINDOW)
       USER INPUT: -departures -t -EU
       TEST 5 EXPECTED OUTPUT:

                                                                   AIRCRAFT TYPES
                ______________________________________________________________________________________________________
                   Type code                                      Aircraft type   Num
                        PC12                    Pilatus PC-12(single-turboprop)     1
                     Unknown                                            Unknown     1
                        C510                              CessnaCitationMustang     1

       TEST 5 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 6 (SAVE CURRENT AIRWINDOW NO FILE)
       USER INPUT: -scw 
       TEST 6 EXPECTED OUTPUT:

       >>> Can not operate without filename.

       TEST 6 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 7 (SAVE CURRENT AIRWINDOW INVALID FILE FORMAT)
       USER INPUT: -scw report
       TEST 7 EXPECTED OUTPUT:

       Invalid file format.

       TEST 7 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 8 (SAVE CURRENT AIRWINDOW)
       USER INPUT: -scw report.bin
       TEST 8 EXPECTED OUTPUT:

       --- no output expected --- file saved to report.bin

       TEST 8 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 9 (-S PREFIX NO FILE)
       USER INPUT: -s -arrivals
       TEST 9 EXPECTED OUTPUT:

       Can not operate without filename and command.

       TEST 9 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 10 (-S PREFIX INVALID COMMAND)
       USER INPUT: -s report.txt -r
       TEST 10 EXPECTED OUTPUT:

       Unsaveable command.

       TEST 10 STATUS: PASSED
       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
       TEST 11 (-S PREFIX SAVING)
       USER INPUT: -s report.txt -home
       TEST 11 EXPECTED OUTPUT:

       --- no output expected --- file saved to report.txt

       TEST 11 STATUS: PASSED
### Popis fungování externí knihovny - Jsoup
Jako externí knihovna byla zvolena knihovna Jsoup Java real-world HTML Parser pro zpracování HTML souborů.\
Program využívá příkaz Jsoup.connect() knihovny pro získání HTML souboru s aktuálními daty ze zadané webové adresy.\
    
    Document doc = Jsoup.connect("link").get();
    String loadString = doc.toString();
     









