package com.service.acmedeliveryfinal.bootstrap;

import com.service.acmedeliveryfinal.base.BaseComponent;
import com.service.acmedeliveryfinal.domain.ProductCategory;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreCategory;
import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.service.ProductCategoryService;
import com.service.acmedeliveryfinal.service.StoreCategoryService;
import com.service.acmedeliveryfinal.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;



@Component
@RequiredArgsConstructor
@Order(1)
public class StoreContentCreator extends BaseComponent implements CommandLineRunner {
    private final ProductCategoryService productCategoryService;
    private final StoreService storeService;
    private final StoreCategoryService storeCategoryService;

    @Override
    public void run(String... args) {
        List<ProductCategory> productCategories = List.of(
                ProductCategory.builder().name("PIZZA").build(),
                ProductCategory.builder().name("BURGERS").build(),
                ProductCategory.builder().name("ΑΝΑΨΥΚΤΙΚΑ").build(),
                ProductCategory.builder().name("DESSERTS").build(),
                ProductCategory.builder().name("PIZZAROSSE").build(),
                ProductCategory.builder().name("DOLCI").build(),
                ProductCategory.builder().name("BEEF BURGERS").build(),//7
                ProductCategory.builder().name("SIGNATURE BURGERS").build(),//8
                ProductCategory.builder().name("CHICKEN BURGERS").build(),//9
                ProductCategory.builder().name("GREEK STYLE BURGERS").build(),//10
                ProductCategory.builder().name("ΤΕΜΑΧΙΑ").build(),//11
                ProductCategory.builder().name("ΤΥΛΙΧΤΑ").build(),//12
                ProductCategory.builder().name("ΤΑ ΣΟΥΒΛΑΚΙΑ ΤΟΥ ΖΑΧΙΡ").build(),//13
                ProductCategory.builder().name("ΠΑΤΑΤΕΣ").build(),//14
                ProductCategory.builder().name("ΚΛΑΣΣΙΚΕΣ ΠΙΤΕΣ").build(),//15
                ProductCategory.builder().name("ΟΡΕΚΤΙΚΑ").build(),//16
                ProductCategory.builder().name("ΣΑΛΑΤΕΣ").build(),//17
                ProductCategory.builder().name("ΜΠΥΡΕΣ").build(),//18
                ProductCategory.builder().name("ΚΑΦΕΔΕΣ").build(),//19
                ProductCategory.builder().name("ΡΟΦΗΜΑΤΑ").build(),//20
                ProductCategory.builder().name("ICED TEA").build(),//21
                ProductCategory.builder().name("SMOOTHIES").build()//22
        );
        logger.info("Create Product Categories:{}", productCategoryService.createAll(productCategories));

        List<StoreCategory> storeCategories = List.of(
                StoreCategory.builder().name("PIZZA").build(),//1
                StoreCategory.builder().name("BURGERS").build(),//2
                StoreCategory.builder().name("ΚΑΦΕΔΕΣ").build(),//3
                StoreCategory.builder().name("ΣΟΥΒΛΑΚΙΑ").build()//4
        );
        logger.info("Create Store Categories:{}", storeCategoryService.createAll(storeCategories));


        StoreItem portofino1= StoreItem.builder().itemName("MARGHERITA").price(BigDecimal.valueOf(10.00)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino2= StoreItem.builder().itemName("PORTOFINO").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino3=StoreItem.builder().itemName("PEPPERONI CLASSIC").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino4=StoreItem.builder().itemName("VEGETARIAN").price(BigDecimal.valueOf(100.00)).productCategory(productCategoryService.get(1L)).build();
        StoreItem portofino5=StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build();
        StoreItem portofino6=StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build();
        StoreItem portofino7=StoreItem.builder().itemName("PANNA COTTA").price(BigDecimal.valueOf(5.00)).productCategory(productCategoryService.get(4L)).build();
        StoreItem portofino8=StoreItem.builder().itemName("PAVLOVA").price(BigDecimal.valueOf(5.00)).productCategory(productCategoryService.get(4L)).build();

        Store portofino = storeService.create(Store.builder().storeName("PORTOFINO").city("ΚΗΦΙΣΙΑ").address("ΛΕΒΙΔΟΥ 16,14562").phoneNumber("2100000000").emailAddress("portofino@gmail.com").category(storeCategoryService.get(1L)).build());

        storeService.addItem(portofino.getId(),portofino1);
        logger.info("Added portofino menu item 1");
        storeService.addItem(portofino.getId(),portofino2);
        logger.info("Added portofino menu item 2");
        storeService.addItem(portofino.getId(),portofino3);
        logger.info("Added portofino menu item 3");
        storeService.addItem(portofino.getId(),portofino4);
        logger.info("Added portofino menu item 4");
        storeService.addItem(portofino.getId(),portofino5);
        logger.info("Added portofino menu item 5");
        storeService.addItem(portofino.getId(),portofino6);
        logger.info("Added portofino menu item 6");
        storeService.addItem(portofino.getId(),portofino7);
        logger.info("Added portofino menu item 7");
        storeService.addItem(portofino.getId(),portofino8);
        logger.info("Added portofino menu item 8");

        Store sogno = storeService.create(Store.builder().storeName("SOGNO").city("ΚΗΦΙΣΙΑ").address("ΦΡΑΓΚΟΠΟΥΛΟΥ 30,14561").phoneNumber("2100000001").emailAddress("sogno@gmail.com").category(storeCategoryService.get(1L)).build());


        StoreItem sognoItem1=StoreItem.builder().itemName("MARGHERITA").price(BigDecimal.valueOf(7.60)).productCategory(productCategoryService.get(5L)).build();
        StoreItem sognoItem2=StoreItem.builder().itemName("SPECIAL").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(5L)).build();
        StoreItem sognoItem3=StoreItem.builder().itemName("AMERICANA").price(BigDecimal.valueOf(12.50)).productCategory(productCategoryService.get(5L)).build();
        StoreItem sognoItem4=StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build();
        StoreItem sognoItem5=StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build();
        StoreItem sognoItem6=StoreItem.builder().itemName("TIRAMISSU").price(BigDecimal.valueOf(7.90)).productCategory(productCategoryService.get(6L)).build();
        StoreItem sognoItem7=StoreItem.builder().itemName("DUO MUSSE").price(BigDecimal.valueOf(9.00)).productCategory(productCategoryService.get(6L)).build();

        storeService.addItem(sogno.getId(),sognoItem1);
        storeService.addItem(sogno.getId(),sognoItem2);
        storeService.addItem(sogno.getId(),sognoItem3);
        storeService.addItem(sogno.getId(),sognoItem4);
        storeService.addItem(sogno.getId(),sognoItem5);
        storeService.addItem(sogno.getId(),sognoItem6);
        storeService.addItem(sogno.getId(),sognoItem7);

        logger.info("Added sogno menu items");

        Store burgeria = storeService.create(Store.builder().storeName("BURGERIA").city("Ν.ΗΡΑΚΛΕΙΟ").address("ΗΛΕΚΤΡΑΣ ΑΠΟΣΤΟΛΟΥ 62, 14122").phoneNumber("2100000002").emailAddress("burgheria@gmail.com").category(storeCategoryService.get(2L)).build());

        StoreItem burgeria1=StoreItem.builder().itemName("CLASSIC BURGER").price(BigDecimal.valueOf(6.90)).productCategory(productCategoryService.get(7L)).build();
        StoreItem burgeria2=StoreItem.builder().itemName("CHEESE AND BACON BURGER").price(BigDecimal.valueOf(7.50)).productCategory(productCategoryService.get(7L)).build();
        StoreItem burgeria3=StoreItem.builder().itemName("THE OLD SCHOOL BURGER").price(BigDecimal.valueOf(10.90)).productCategory(productCategoryService.get(7L)).build();
        StoreItem burgeria4=StoreItem.builder().itemName("ITALIAN JOB BURGER").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(8L)).build();
        StoreItem burgeria5=StoreItem.builder().itemName("LUSSO BLUE BURGER").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(8L)).build();
        StoreItem burgeria6=StoreItem.builder().itemName("PURE CHICKEN BURGER").price(BigDecimal.valueOf(6.90)).productCategory(productCategoryService.get(9L)).build();
        StoreItem burgeria7=StoreItem.builder().itemName("AVOCADO JUICY CHICKEN BURGER").price(BigDecimal.valueOf(7.50)).productCategory(productCategoryService.get(9L)).build();

        storeService.addItem(burgeria.getId(),burgeria1);
        storeService.addItem(burgeria.getId(),burgeria2);
        storeService.addItem(burgeria.getId(),burgeria3);
        storeService.addItem(burgeria.getId(),burgeria4);
        storeService.addItem(burgeria.getId(),burgeria5);
        storeService.addItem(burgeria.getId(),burgeria6);
        storeService.addItem(burgeria.getId(),burgeria7);

        logger.info("Added burgeria menu items");

        Store fatto = storeService.create(Store.builder().storeName("FATTO A MANO").city("ΠΕΥΚΗ").address("ΔΗΜΟΚΡΑΤΙΑΣ 3, 151212").phoneNumber("2100000003").emailAddress("burgery@gmail.com").category(storeCategoryService.get(2L)).build());

        StoreItem fatto1=StoreItem.builder().itemName("CLASSIC CHEESEBURGER").price(BigDecimal.valueOf(7.90)).productCategory(productCategoryService.get(7L)).build();
        StoreItem fatto2=StoreItem.builder().itemName("BLUE BACON BURGER").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(7L)).build();
        StoreItem fatto3=StoreItem.builder().itemName("JUST MUST BURGER").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(7L)).build();
        StoreItem fatto4=StoreItem.builder().itemName("GREEK BURGER ΓΥΡΟΣ ΧΟΙΡΙΝΟΣ").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(10L)).build();
        StoreItem fatto5=StoreItem.builder().itemName("GREEK BURGER ΓΥΡΟΣ ΚΟΤΟΠΟΥΛΟΣ").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(10L)).build();
        StoreItem fatto6=StoreItem.builder().itemName("BILLY THE KID BURGER").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(9L)).build();
        StoreItem fatto7=StoreItem.builder().itemName("AL CAPONE BURGER").price(BigDecimal.valueOf(9.60)).productCategory(productCategoryService.get(9L)).build();

        storeService.addItem(fatto.getId(),fatto1);
        storeService.addItem(fatto.getId(),fatto2);
        storeService.addItem(fatto.getId(),fatto3);
        storeService.addItem(fatto.getId(),fatto4);
        storeService.addItem(fatto.getId(),fatto5);
        storeService.addItem(fatto.getId(),fatto6);
        storeService.addItem(fatto.getId(),fatto7);

        storeService.addItem(1L, StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build());
        logger.info("Added BBQ pizza to Portofino");

        //works ok.
        //storeService.removeItem(1L, 5L); //ok no cascade.
        //logger.info("Removed Coca-Cola 330 ml from Portofino");

        //storeService.updateItem(1L,StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(14.00)).productCategory(productCategoryService.get(1L)).build() );

        //ok cascades only to storeItems.
        //storeService.delete(storeService.get(1L));

        //Not allowed child records found-ok.
        //storeCategoryService.deleteById(1L);

        Store streetSouvlaki = storeService.create(Store.builder().storeName("STREET SOUVLAKI").city("ΑΘΗΝΑ").address("ΚΟΛΟΚΟΤΡΩΝΗ 30, 10561").phoneNumber("2100000004").emailAddress("streetsouvlaki@gmail.com").category(storeCategoryService.get(4L)).build());

        //31
        StoreItem streetSouvlaki1=StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΧΟΙΡΙΝΟ").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(11L)).build();
        StoreItem streetSouvlaki2= StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΚΟΤΟΠΟΥΛΟ").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(11L)).build();
        StoreItem streetSouvlaki3=StoreItem.builder().itemName("ΚΟΤΟΜΠΕΙΚΟΝ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(11L)).build();
        StoreItem streetSouvlaki4=StoreItem.builder().itemName("ΚΕΜΠΑΠ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(11L)).build();
        StoreItem streetSouvlaki5=StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΧΟΙΡΙΝΟ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(12L)).build();
        StoreItem streetSouvlaki6=StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΚΟΤΟΠΟΥΛΟ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(12L)).build();
        StoreItem streetSouvlaki7=StoreItem.builder().itemName("ΚΕΜΠΑΠ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(12L)).build();
        StoreItem streetSouvlaki8=StoreItem.builder().itemName("ΜΠΙΦΤΕΚΙ ΛΑΧΑΝΙΚΩΝ ΣΕ ΑΡΑΒΙΚΟ ΑΡΤΟ").price(BigDecimal.valueOf(3.20)).productCategory(productCategoryService.get(12L)).build();
        StoreItem streetSouvlaki9=StoreItem.builder().itemName("CAESARS ΚΟΤΟΠΟΥΛΟ ΣΕ ΑΡΑΒΙΚΟ ΑΡΤΟ").price(BigDecimal.valueOf(3.20)).productCategory(productCategoryService.get(12L)).build();
        StoreItem streetSouvlaki10=StoreItem.builder().itemName("ΠΑΤΑΤΕΣ ΤΗΓΑΝΗΤΕΣ").price(BigDecimal.valueOf(3.50)).productCategory(productCategoryService.get(13L)).build();
        StoreItem streetSouvlaki11=StoreItem.builder().itemName("ΠΑΤΑΤΕΣ ΤΗΓΑΝΗΤΕΣ ΤΥΡΑΤΕΣ").price(BigDecimal.valueOf(4.50)).productCategory(productCategoryService.get(13L)).build();
        StoreItem streetSouvlaki12=StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.50)).productCategory(productCategoryService.get(3L)).build();
        StoreItem streetSouvlaki13=StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.50)).productCategory(productCategoryService.get(3L)).build();

        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki1);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki2);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki3);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki4);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki5);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki6);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki7);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki8);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki9);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki10);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki11);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki12);
        storeService.addItem(streetSouvlaki.getId(),streetSouvlaki13);

        Store pitaPan = storeService.create(Store.builder().storeName("PITA PAN").city("ΑΘΗΝΑ").address("ΛΕΩΦΟΡΟΣ ΒΟΥΛΙΑΓΜΕΝΗΣ 276, 17343").phoneNumber("2100000005").emailAddress("pitapan@gmail.com").category(storeCategoryService.get(4L)).build());

        StoreItem pitaPan1=StoreItem.builder().itemName("ΓΥΡΟΣ ΧΟΙΡΙΝΟΣ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.20)).productCategory(productCategoryService.get(14L)).build();
        StoreItem pitaPan2=StoreItem.builder().itemName("ΦΡΕΣΚΟ ΚΑΛΑΜΑΚΙ ΧΟΙΡΙΝΟ").price(BigDecimal.valueOf(1.90)).productCategory(productCategoryService.get(14L)).build();
        StoreItem pitaPan3=StoreItem.builder().itemName("ΚΕΜΠΑΠ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(14L)).build();
        StoreItem pitaPan4=StoreItem.builder().itemName("ΧΑΛΟΥΜΙ ΣΤΗ ΣΧΑΡΑ").price(BigDecimal.valueOf(5.40)).productCategory(productCategoryService.get(16L)).build();
        StoreItem pitaPan5=StoreItem.builder().itemName("ΤΖΑΤΖΙΚΙ ΠΑΡΑΔΟΣΙΑΚΟ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(16L)).build();
        StoreItem pitaPan6=StoreItem.builder().itemName("ΠΑΤΑΤΕΣ ΧΩΡΙΑΤΙΚΕΣ").price(BigDecimal.valueOf(3.50)).productCategory(productCategoryService.get(16L)).build();
        StoreItem pitaPan7=StoreItem.builder().itemName("ΠΟΛΥΧΡΩΜΗ").price(BigDecimal.valueOf(4.50)).productCategory(productCategoryService.get(17L)).build();
        StoreItem pitaPan8=StoreItem.builder().itemName("ΠΡΑΣΙΝΗ").price(BigDecimal.valueOf(4.50)).productCategory(productCategoryService.get(17L)).build();
        StoreItem pitaPan9=StoreItem.builder().itemName("ΧΑΛΟΥΜΙ").price(BigDecimal.valueOf(4.60)).productCategory(productCategoryService.get(17L)).build();
        StoreItem pitaPan10=StoreItem.builder().itemName("ALPHA ΚΟΥΤΙ 330 ML").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(18L)).build();
        StoreItem pitaPan11=StoreItem.builder().itemName("HEINEKEN ΚΟΥΤΙ 330 ML").price(BigDecimal.valueOf(2.50)).productCategory(productCategoryService.get(18L)).build();
        StoreItem pitaPan12=StoreItem.builder().itemName("AMSTEL ΚΟΥΤΙ 330 ML").price(BigDecimal.valueOf(2.40)).productCategory(productCategoryService.get(18L)).build();

        storeService.addItem(pitaPan.getId(),pitaPan1);
        storeService.addItem(pitaPan.getId(),pitaPan2);
        storeService.addItem(pitaPan.getId(),pitaPan3);
        storeService.addItem(pitaPan.getId(),pitaPan4);
        storeService.addItem(pitaPan.getId(),pitaPan5);
        storeService.addItem(pitaPan.getId(),pitaPan6);
        storeService.addItem(pitaPan.getId(),pitaPan7);
        storeService.addItem(pitaPan.getId(),pitaPan8);
        storeService.addItem(pitaPan.getId(),pitaPan9);
        storeService.addItem(pitaPan.getId(),pitaPan10);
        storeService.addItem(pitaPan.getId(),pitaPan11);
        storeService.addItem(pitaPan.getId(),pitaPan12);

        Store malePlus = storeService.create(Store.builder().storeName("MALE PLUS-ΔΑΦΝΗ").city("ΑΘΗΝΑ").address("ΕΛΛΗΣ 15, 17235").phoneNumber("2100000006").emailAddress("maleplus@gmail.com").category(storeCategoryService.get(3L)).build());

        StoreItem malePlus1=StoreItem.builder().itemName("FREDDO ESPRESSO").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(19L)).build();
        StoreItem malePlus2=StoreItem.builder().itemName("ESPRESSO RISTRETTO").price(BigDecimal.valueOf(1.40)).productCategory(productCategoryService.get(19L)).build();
        StoreItem malePlus3=StoreItem.builder().itemName("FRAPPE").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(19L)).build();
        StoreItem malePlus4=StoreItem.builder().itemName("ΦΙΛΤΡΟΥ").price(BigDecimal.valueOf(1.80)).productCategory(productCategoryService.get(19L)).build();
        StoreItem malePlus5=StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(20L)).build();
        StoreItem malePlus6=StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ BUENO").price(BigDecimal.valueOf(2.60)).productCategory(productCategoryService.get(20L)).build();
        StoreItem malePlus7=StoreItem.builder().itemName("MOCACCHINO").price(BigDecimal.valueOf(2.90)).productCategory(productCategoryService.get(20L)).build();
        StoreItem malePlus8=StoreItem.builder().itemName("FREDDOCHINO").price(BigDecimal.valueOf(2.50)).productCategory(productCategoryService.get(20L)).build();
        StoreItem malePlus9=StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.30)).productCategory(productCategoryService.get(3L)).build();
        StoreItem malePlus10=StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.30)).productCategory(productCategoryService.get(3L)).build();

        storeService.addItem(malePlus.getId(),malePlus1);
        storeService.addItem(malePlus.getId(),malePlus2);
        storeService.addItem(malePlus.getId(),malePlus3);
        storeService.addItem(malePlus.getId(),malePlus4);
        storeService.addItem(malePlus.getId(),malePlus5);
        storeService.addItem(malePlus.getId(),malePlus6);
        storeService.addItem(malePlus.getId(),malePlus7);
        storeService.addItem(malePlus.getId(),malePlus8);
        storeService.addItem(malePlus.getId(),malePlus9);
        storeService.addItem(malePlus.getId(),malePlus10);

        Store kobra = storeService.create(Store.builder().storeName("KOBRA-ΑΚΑΔΗΜΙΑ").city("ΑΘΗΝΑ").address("ΝΑΥΑΡΙΝΟΥ 11,10679").phoneNumber("2100000007").emailAddress("kobra@gmail.com").category(storeCategoryService.get(3L)).build());
        StoreItem kobra1=StoreItem.builder().itemName("ICED TEA").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(21L)).build();
        StoreItem kobra2=StoreItem.builder().itemName("ICED TEA ΦΑΣΚΟΜΗΛΟ").price(BigDecimal.valueOf(4.70)).productCategory(productCategoryService.get(21L)).build();
        StoreItem kobra3=StoreItem.builder().itemName("ΙCED TEA FLORIDA").price(BigDecimal.valueOf(5.50)).productCategory(productCategoryService.get(21L)).build();
        StoreItem kobra4=StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ").price(BigDecimal.valueOf(3.50)).productCategory(productCategoryService.get(20L)).build();
        StoreItem kobra5=StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ ΦΟΥΝΤΟΥΚΙ").price(BigDecimal.valueOf(4.00)).productCategory(productCategoryService.get(20L)).build();
        StoreItem kobra6=StoreItem.builder().itemName("SMOOTHIE COFFEE HEAD").price(BigDecimal.valueOf(4.60)).productCategory(productCategoryService.get(22L)).build();
        StoreItem kobra7=StoreItem.builder().itemName("SMOOTHIE MORNING GLORY").price(BigDecimal.valueOf(6.20)).productCategory(productCategoryService.get(22L)).build();
        StoreItem kobra8=StoreItem.builder().itemName("SMOOTHIE GOT RUM").price(BigDecimal.valueOf(5.80)).productCategory(productCategoryService.get(22L)).build();

        storeService.addItem(kobra.getId(),kobra1);
        storeService.addItem(kobra.getId(),kobra2);
        storeService.addItem(kobra.getId(),kobra3);
        storeService.addItem(kobra.getId(),kobra4);
        storeService.addItem(kobra.getId(),kobra5);
        storeService.addItem(kobra.getId(),kobra6);
        storeService.addItem(kobra.getId(),kobra7);
        storeService.addItem(kobra.getId(),kobra8);

    }
}

