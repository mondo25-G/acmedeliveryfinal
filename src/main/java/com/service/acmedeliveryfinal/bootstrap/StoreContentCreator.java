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
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


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

        Set<StoreItem> sognoMenu = Set.of(
                StoreItem.builder().itemName("MARGHERITA").price(BigDecimal.valueOf(7.60)).productCategory(productCategoryService.get(5L)).build(),
                StoreItem.builder().itemName("SPECIAL").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(5L)).build(),
                StoreItem.builder().itemName("AMERICANA").price(BigDecimal.valueOf(12.50)).productCategory(productCategoryService.get(5L)).build(),
                StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build(),
                StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(3L)).build(),
                StoreItem.builder().itemName("TIRAMISSU").price(BigDecimal.valueOf(7.90)).productCategory(productCategoryService.get(6L)).build(),
                StoreItem.builder().itemName("DUO MUSSE").price(BigDecimal.valueOf(9.00)).productCategory(productCategoryService.get(6L)).build())
                ;

        storeService.addItems(storeService.get(2L), sognoMenu);

        logger.info("Added sogno menu items");

        Store burgeria = storeService.create(Store.builder().storeName("BURGERIA").city("Ν.ΗΡΑΚΛΕΙΟ").address("ΗΛΕΚΤΡΑΣ ΑΠΟΣΤΟΛΟΥ 62, 14122").phoneNumber("2100000002").emailAddress("burgheria@gmail.com").category(storeCategoryService.get(2L)).build());

        Set<StoreItem> burgeriaMenu = Set.of(
                StoreItem.builder().itemName("CLASSIC BURGER").price(BigDecimal.valueOf(6.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("CHEESE AND BACON BURGER").price(BigDecimal.valueOf(7.50)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("THE OLD SCHOOL BURGER").price(BigDecimal.valueOf(10.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("ITALIAN JOB BURGER").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(8L)).build(),
                StoreItem.builder().itemName("LUSSO BLUE BURGER").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(8L)).build(),
                StoreItem.builder().itemName("PURE CHICKEN BURGER").price(BigDecimal.valueOf(6.90)).productCategory(productCategoryService.get(9L)).build(),
                StoreItem.builder().itemName("AVOCADO JUICY CHICKEN BURGER").price(BigDecimal.valueOf(7.50)).productCategory(productCategoryService.get(9L)).build())
                ;

        storeService.addItems(storeService.get(3L), burgeriaMenu);

        Store burgery = storeService.create(Store.builder().storeName("FATTO A MANO").city("ΠΕΥΚΗ").address("ΔΗΜΟΚΡΑΤΙΑΣ 3, 151212").phoneNumber("2100000003").emailAddress("burgery@gmail.com").category(storeCategoryService.get(2L)).build());

        Set<StoreItem> burgeryMenu = Set.of(
                StoreItem.builder().itemName("CLASSIC CHEESEBURGER").price(BigDecimal.valueOf(7.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("BLUE BACON BURGER").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("JUST MUST BURGER").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(7L)).build(),
                StoreItem.builder().itemName("GREEK BURGER ΓΥΡΟΣ ΧΟΙΡΙΝΟΣ").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(10L)).build(),
                StoreItem.builder().itemName("GREEK BURGER ΓΥΡΟΣ ΚΟΤΟΠΟΥΛΟΣ").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(10L)).build(),
                StoreItem.builder().itemName("BILLY THE KID BURGER").price(BigDecimal.valueOf(8.90)).productCategory(productCategoryService.get(9L)).build(),
                StoreItem.builder().itemName("AL CAPONE BURGER").price(BigDecimal.valueOf(9.60)).productCategory(productCategoryService.get(9L)).build())
                ;

        storeService.addItems(storeService.get(4L), burgeriaMenu);


        storeService.addItem(1L, StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(11.50)).productCategory(productCategoryService.get(1L)).build());
        logger.info("Added BBQ pizza to Portofino");

        //storeService.removeItem(1L, 5L); //ok no cascade.
        //logger.info("Removed Coca-Cola 330 ml from Portofino");

        //needs storeItem repository
        //storeService.updateItem(1L,StoreItem.builder().itemName("BBQ").price(BigDecimal.valueOf(14.00)).productCategory(productCategoryService.get(1L)).build() );

        //ok cascades only to storeItems.
        //storeService.delete(storeService.get(1L));

        Store streetSouvlaki = storeService.create(Store.builder().storeName("STREET SOUVLAKI").city("ΑΘΗΝΑ").address("ΚΟΛΟΚΟΤΡΩΝΗ 30, 10561").phoneNumber("2100000004").emailAddress("streetsouvlaki@gmail.com").category(storeCategoryService.get(4L)).build());

        Set<StoreItem> streetSouvlakiMenu = Set.of(
                StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΧΟΙΡΙΝΟ").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(11L)).build(),
                StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΚΟΤΟΠΟΥΛΟ").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(11L)).build(),
                StoreItem.builder().itemName("ΚΟΤΟΜΠΕΙΚΟΝ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(11L)).build(),
                StoreItem.builder().itemName("ΚΕΜΠΑΠ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(11L)).build(),
                StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΧΟΙΡΙΝΟ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(12L)).build(),
                StoreItem.builder().itemName("ΚΑΛΑΜΑΚΙ ΚΟΤΟΠΟΥΛΟ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(12L)).build(),
                StoreItem.builder().itemName("ΚΕΜΠΑΠ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(12L)).build(),
                StoreItem.builder().itemName("ΜΠΙΦΤΕΚΙ ΛΑΧΑΝΙΚΩΝ ΣΕ ΑΡΑΒΙΚΟ ΑΡΤΟ").price(BigDecimal.valueOf(3.20)).productCategory(productCategoryService.get(12L)).build(),
                StoreItem.builder().itemName("CAESARS ΚΟΤΟΠΟΥΛΟ ΣΕ ΑΡΑΒΙΚΟ ΑΡΤΟ").price(BigDecimal.valueOf(3.20)).productCategory(productCategoryService.get(12L)).build(),
                StoreItem.builder().itemName("ΠΑΤΑΤΕΣ ΤΗΓΑΝΗΤΕΣ").price(BigDecimal.valueOf(3.50)).productCategory(productCategoryService.get(13L)).build(),
                StoreItem.builder().itemName("ΠΑΤΑΤΕΣ ΤΗΓΑΝΗΤΕΣ ΤΥΡΑΤΕΣ").price(BigDecimal.valueOf(4.50)).productCategory(productCategoryService.get(13L)).build(),
                StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.50)).productCategory(productCategoryService.get(3L)).build(),
                StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.50)).productCategory(productCategoryService.get(3L)).build()

        );
        storeService.addItems(storeService.get(5L), streetSouvlakiMenu);

        Store pitaPan = storeService.create(Store.builder().storeName("PITA PAN").city("ΑΘΗΝΑ").address("ΛΕΩΦΟΡΟΣ ΒΟΥΛΙΑΓΜΕΝΗΣ 276, 17343").phoneNumber("2100000005").emailAddress("pitapan@gmail.com").category(storeCategoryService.get(4L)).build());

        Set<StoreItem> pitaPanMenu = Set.of(
                StoreItem.builder().itemName("ΓΥΡΟΣ ΧΟΙΡΙΝΟΣ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(3.20)).productCategory(productCategoryService.get(14L)).build(),
                StoreItem.builder().itemName("ΦΡΕΣΚΟ ΚΑΛΑΜΑΚΙ ΧΟΙΡΙΝΟ").price(BigDecimal.valueOf(1.90)).productCategory(productCategoryService.get(14L)).build(),
                StoreItem.builder().itemName("ΚΕΜΠΑΠ ΣΕ ΠΙΤΑ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(14L)).build(),
                StoreItem.builder().itemName("ΧΑΛΟΥΜΙ ΣΤΗ ΣΧΑΡΑ").price(BigDecimal.valueOf(5.40)).productCategory(productCategoryService.get(16L)).build(),
                StoreItem.builder().itemName("ΤΖΑΤΖΙΚΙ ΠΑΡΑΔΟΣΙΑΚΟ").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(16L)).build(),
                StoreItem.builder().itemName("ΠΑΤΑΤΕΣ ΧΩΡΙΑΤΙΚΕΣ").price(BigDecimal.valueOf(3.50)).productCategory(productCategoryService.get(16L)).build(),
                StoreItem.builder().itemName("ΠΟΛΥΧΡΩΜΗ").price(BigDecimal.valueOf(4.50)).productCategory(productCategoryService.get(17L)).build(),
                StoreItem.builder().itemName("ΠΡΑΣΙΝΗ").price(BigDecimal.valueOf(4.50)).productCategory(productCategoryService.get(17L)).build(),
                StoreItem.builder().itemName("ΧΑΛΟΥΜΙ").price(BigDecimal.valueOf(4.60)).productCategory(productCategoryService.get(17L)).build(),
                StoreItem.builder().itemName("ALPHA ΚΟΥΤΙ 330 ML").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(18L)).build(),
                StoreItem.builder().itemName("HEINEKEN ΚΟΥΤΙ 330 ML").price(BigDecimal.valueOf(2.50)).productCategory(productCategoryService.get(18L)).build(),
                StoreItem.builder().itemName("AMSTEL ΚΟΥΤΙ 330 ML").price(BigDecimal.valueOf(2.40)).productCategory(productCategoryService.get(18L)).build()
        );
        storeService.addItems(storeService.get(6L), pitaPanMenu);

        Store malePlus = storeService.create(Store.builder().storeName("MALE PLUS-ΔΑΦΝΗ").city("ΑΘΗΝΑ").address("ΕΛΛΗΣ 15, 17235").phoneNumber("2100000006").emailAddress("maleplus@gmail.com").category(storeCategoryService.get(3L)).build());

        Set<StoreItem> malePlusMenu = Set.of(
                StoreItem.builder().itemName("FREDDO ESPRESSO").price(BigDecimal.valueOf(2.00)).productCategory(productCategoryService.get(19L)).build(),
                StoreItem.builder().itemName("ESPRESSO RISTRETTO").price(BigDecimal.valueOf(1.40)).productCategory(productCategoryService.get(19L)).build(),
                StoreItem.builder().itemName("FRAPPE").price(BigDecimal.valueOf(1.70)).productCategory(productCategoryService.get(19L)).build(),
                StoreItem.builder().itemName("ΦΙΛΤΡΟΥ").price(BigDecimal.valueOf(1.80)).productCategory(productCategoryService.get(19L)).build(),
                StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ").price(BigDecimal.valueOf(2.20)).productCategory(productCategoryService.get(20L)).build(),
                StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ BUENO").price(BigDecimal.valueOf(2.60)).productCategory(productCategoryService.get(20L)).build(),
                StoreItem.builder().itemName("MOCACCHINO").price(BigDecimal.valueOf(2.90)).productCategory(productCategoryService.get(20L)).build(),
                StoreItem.builder().itemName("FREDDOCHINO").price(BigDecimal.valueOf(2.50)).productCategory(productCategoryService.get(20L)).build(),
                StoreItem.builder().itemName("COCA-COLA 330 ML").price(BigDecimal.valueOf(1.30)).productCategory(productCategoryService.get(3L)).build(),
                StoreItem.builder().itemName("COCA-COLA LIGHT 330 ML").price(BigDecimal.valueOf(1.30)).productCategory(productCategoryService.get(3L)).build()
        );
        storeService.addItems(storeService.get(7L), malePlusMenu);

        Store kobra = storeService.create(Store.builder().storeName("KOBRA-ΑΚΑΔΗΜΙΑ").city("ΑΘΗΝΑ").address("ΝΑΥΑΡΙΝΟΥ 11,10679").phoneNumber("2100000007").emailAddress("kobra@gmail.com").category(storeCategoryService.get(3L)).build());
        Set<StoreItem>  kobraMenu= Set.of(
                StoreItem.builder().itemName("ICED TEA").price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.get(21L)).build(),
                StoreItem.builder().itemName("ICED TEA ΦΑΣΚΟΜΗΛΟ").price(BigDecimal.valueOf(4.70)).productCategory(productCategoryService.get(21L)).build(),
                StoreItem.builder().itemName("ΙCED TEA FLORIDA").price(BigDecimal.valueOf(5.50)).productCategory(productCategoryService.get(21L)).build(),
                StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ").price(BigDecimal.valueOf(3.50)).productCategory(productCategoryService.get(20L)).build(),
                StoreItem.builder().itemName("ΣΟΚΟΛΑΤΑ ΦΟΥΝΤΟΥΚΙ").price(BigDecimal.valueOf(4.00)).productCategory(productCategoryService.get(20L)).build(),
                StoreItem.builder().itemName("SMOOTHIE COFFEE HEAD").price(BigDecimal.valueOf(4.60)).productCategory(productCategoryService.get(22L)).build(),
                StoreItem.builder().itemName("SMOOTHIE MORNING GLORY").price(BigDecimal.valueOf(6.20)).productCategory(productCategoryService.get(22L)).build(),
                StoreItem.builder().itemName("SMOOTHIE GOT RUM").price(BigDecimal.valueOf(5.80)).productCategory(productCategoryService.get(22L)).build()
        );
        storeService.addItems(storeService.get(8L), kobraMenu);

    }
}

