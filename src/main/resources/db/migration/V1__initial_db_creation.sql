create table if not exists users
(
    user_type               varchar(31) not null,
    id                      uuid        not null
        primary key,
    username                varchar(255),
    account_non_expired     boolean,
    account_non_locked      boolean,
    enabled                 boolean,
    role                    varchar(255),
    credentials_non_expired boolean,
    password                varchar(255),
    email                   varchar(255),
    first_name              varchar(255),
    last_name               varchar(255),
    phone_number            varchar(255),
    web_page                varchar(255),
    keycloak_id             varchar(255)
        constraint uk_366dgrd625s5659shyen79mmw
            unique
);

create table if not exists seller
(
    id        uuid    not null
        primary key,
    capacity  smallint
        constraint seller_capacity_check
            check ((capacity >= 0) AND (capacity <= 3)),
    is_active boolean not null,
    user_id   uuid
        constraint uk_etfpl3vymasxfqc4ri4r3euf6
            unique
        constraint fk614u1eblpnxmrxd25efo29qhr
            references users
);

create table if not exists cart
(
    id                 uuid not null
    primary key,
    created_by         varchar(255),
    created_date       timestamp(6) with time zone,
                                        last_modified_by   varchar(255),
    last_modified_date timestamp(6) with time zone,
                                        customer_id UUID UNIQUE
                                        );

create table if not exists customer
(
    id                                        uuid not null
        primary key,
    city                                      varchar(255),
    country                                   varchar(255),
    state                                     varchar(255),
    street                                    varchar(255),
    zip_code                                  varchar(255),
    age_group                                 varchar(255),
    brand_preferences                         varchar(255),
    clothing_size_determination               varchar(255),
    medical_examination                       varchar(255),
    skin_disease                              varchar(255),
    average_arm_length                        integer,
    average_length_between_waist_and_buttocks integer,
    average_outside_leg_length                integer,
    average_shoulder_length                   integer,
    average_thigh_circumference               integer,
    back_waist_length                         integer,
    back_width                                integer,
    buttock_circumference                     integer,
    chest_circumference                       integer,
    chest_circumference_horizontal            integer,
    chest_height                              integer,
    chest_span                                integer,
    chest_width                               integer,
    front_length                              integer,
    front_waist_length                        integer,
    height                                    integer,
    height_buttocks                           integer,
    hip_circumference                         integer,
    hip_height                                integer,
    inner_leg_length                          integer,
    left_arm_length                           integer,
    left_outer_leg_length                     integer,
    left_shoulder_inclination                 real,
    left_shoulder_length                      integer,
    left_thigh_circumference                  integer,
    left_waist_to_hip_length                  integer,
    length_left_waist_to_buttocks             integer,
    length_of_chest_at_back                   integer,
    neck_circumference                        integer,
    right_arm_length                          integer,
    right_left_waist_to_buttocks              integer,
    right_shoulder_inclination                real,
    right_shoulder_length                     integer,
    right_thigh_circumference                 integer,
    right_waist_to_hip_length                 integer,
    righto_outer_leg_length                   integer,
    underbust_measurement                     integer,
    upper_arm_circumference                   integer,
    waist_circumference                       integer,
    waist_to_hip_length_on_average            integer,
    wrist_circumference                       integer,
    user_id UUID UNIQUE,
    cart_id UUID UNIQUE
);

ALTER TABLE cart
    ADD CONSTRAINT fk_cart_customer
        FOREIGN KEY (customer_id) REFERENCES customer(id);


ALTER TABLE customer
    ADD CONSTRAINT fk_customer_cart
        FOREIGN KEY (cart_id) REFERENCES cart(id);


create table if not exists product
(
    id                    uuid not null
    primary key,
    adult_age_group       varchar(255)
    constraint product_adult_age_group_check
    check ((adult_age_group)::text = ANY
((ARRAY ['NINETEEN_TO_TWENTYNINE'::character varying, 'THIRTY_TO_FOURTYFIVE'::character varying, 'FOURTYSIX_TO_SIXTYSEVEN'::character varying, 'GREATER_THAN_SIXTYESEVEN'::character varying])::text[])),
    children_age_group    varchar(255)
    constraint product_children_age_group_check
    check ((children_age_group)::text = ANY
((ARRAY ['SMALLER_THAN_SIX_MONTHS'::character varying, 'SIX_MONTHS_TO_TWO_YEAR'::character varying, 'TWO_TO_SIX_YEARS'::character varying, 'SEVEN_TO_THIRTEEN_YEARS'::character varying, 'FOURTEEN_TO_EIGHTEEN_YEARS'::character varying])::text[])),
    application_area      varchar(255)
    constraint product_application_area_check
    check ((application_area)::text = ANY
((ARRAY ['PROFESSIONAL_CLOTHING'::character varying, 'SLEEP_AND_REST_TIME'::character varying, 'OUTDOOR'::character varying, 'WOUND_HEALING'::character varying, 'BANDAGE_MATERIAL'::character varying, 'SCRATCH_PROTECTION'::character varying, 'THERAPY_SUPPORT'::character varying, 'SUN_PROTECTION'::character varying, 'HEAT_PROTECTION'::character varying, 'MECHANICAL_PROTECTION'::character varying, 'BED_PROTECTION'::character varying, 'UNDERWEAR'::character varying, 'HOODIES_AND_PULLOVERS'::character varying, 'OCCASION_AND_FESTIVE'::character varying, 'ACCESSORIES_FOR_BABIES_AND_CHILDREN'::character varying, 'PANTS_AND_SHORTS_AND_JUMPSUITS'::character varying, 'SWIMWEAR'::character varying, 'OTHERS'::character varying])::text[])),
    usage_cycle           varchar(255)
    constraint product_usage_cycle_check
    check ((usage_cycle)::text = ANY
((ARRAY ['ONE_TIME'::character varying, 'TWO_TO_NINE'::character varying, 'TEN_TO_FOURTYNINE'::character varying, 'FIFTY_TO_HUNDRED'::character varying, 'MORE_THAN_HUNDRED'::character varying])::text[])),
    brand                 varchar(255),
    category              varchar(255)
    constraint product_category_check
    check ((category)::text = ANY
((ARRAY ['JEANS'::character varying, 'T_SHIRT'::character varying, 'SHIRT'::character varying, 'JACKET'::character varying, 'COAT'::character varying, 'HOODIE'::character varying, 'SWEATSHIRT'::character varying, 'DRESS'::character varying, 'SKIRT'::character varying, 'SHORTS'::character varying, 'PANTS'::character varying, 'LEGGINGS'::character varying, 'JUMPSUIT'::character varying, 'SWIMSUIT'::character varying, 'UNDERWEAR'::character varying, 'SOCKS'::character varying, 'SHOES'::character varying, 'ACCESSORIES'::character varying])::text[])),
    color                 varchar(255)
    constraint product_color_check
    check ((color)::text = ANY
((ARRAY ['RED'::character varying, 'GREEN'::character varying, 'BLUE'::character varying, 'WHITE'::character varying, 'BLACK'::character varying])::text[])),
    description           varchar(2048),
    design_appearance     varchar(255)
    constraint product_design_appearance_check
    check ((design_appearance)::text = ANY
((ARRAY ['DYNAMIC'::character varying, 'CEREMONIAL'::character varying, 'INNOVATIVE'::character varying, 'CLASSIC'::character varying, 'PRIMITIVE'::character varying, 'PROVOCATIVE'::character varying, 'ROMANTIC'::character varying, 'FACTUAL'::character varying, 'SIMPLE'::character varying, 'STATIC'::character varying, 'TECHNICAL'::character varying, 'TRADITIONAL'::character varying, 'LUXURIOUS'::character varying, 'RESTRAINED'::character varying])::text[])),
    design_color          varchar(255)
    constraint product_design_color_check
    check ((design_color)::text = ANY
((ARRAY ['COLORFUL'::character varying, 'NON_COLORFUL'::character varying, 'PATTERNED'::character varying, 'RAW_WHITE_STRETCHED'::character varying, 'RAW_WHITE_WASHED'::character varying, 'WHITE'::character varying, 'BLACK'::character varying])::text[])),
    gender                varchar(255)
    constraint product_gender_check
    check ((gender)::text = ANY
((ARRAY ['MALE'::character varying, 'FEMALE'::character varying, 'UNISEX'::character varying])::text[])),
    elasticity            varchar(255)
    constraint product_elasticity_check
    check ((elasticity)::text = ANY
((ARRAY ['VERY_ELASTIC'::character varying, 'ELASTIC'::character varying, 'SLIGHTLY_ELASTIC'::character varying, 'SOMEWHAT_STIFF'::character varying, 'VERY_STIFF'::character varying])::text[])),
    fineness              varchar(255)
    constraint product_fineness_check
    check ((fineness)::text = ANY
((ARRAY ['VERY_DELICATE'::character varying, 'VERY_FINE'::character varying, 'FINE'::character varying, 'COARSE'::character varying, 'VERY_COARSE'::character varying])::text[])),
    lightweight           varchar(255)
    constraint product_lightweight_check
    check ((lightweight)::text = ANY
((ARRAY ['ULTRA_LIGHT'::character varying, 'VERY_LIGHT'::character varying, 'LIGHT'::character varying, 'HEAVY'::character varying, 'VERY_HEAVY'::character varying])::text[])),
    lint_free             varchar(255)
    constraint product_lint_free_check
    check ((lint_free)::text = ANY
((ARRAY ['FREE'::character varying, 'ALMOST_FREE'::character varying, 'SOMEWHAT_FREE'::character varying, 'NOT_FREE'::character varying, 'HIGHLY_LINTY'::character varying])::text[])),
    scratchy              varchar(255)
    constraint product_scratchy_check
    check ((scratchy)::text = ANY
((ARRAY ['VERY_SCRATCHY'::character varying, 'SCRATCHY'::character varying, 'ROUGH'::character varying, 'SMOOTH'::character varying, 'VELVETY'::character varying])::text[])),
    seam_feelable         varchar(255)
    constraint product_seam_feelable_check
    check ((seam_feelable)::text = ANY
((ARRAY ['NOT_FEELABLE'::character varying, 'BARELY_FEELABLE'::character varying, 'FEELABLE'::character varying, 'VERY_FEELABLE'::character varying])::text[])),
    softness              varchar(255)
    constraint product_softness_check
    check ((softness)::text = ANY
((ARRAY ['VERY_SOFT'::character varying, 'SOFT'::character varying, 'MODERATE'::character varying, 'HARD'::character varying, 'VERY_HARD'::character varying])::text[])),
    uniform               varchar(255)
    constraint product_uniform_check
    check ((uniform)::text = ANY
((ARRAY ['COMPLETELY'::character varying, 'MOSTLY'::character varying, 'IRREGULAR'::character varying, 'BROKEN'::character varying, 'VERY_UNEVEN'::character varying])::text[])),
    image_url             varchar(255),
    abrasion_resistant    varchar(255)
    constraint product_abrasion_resistant_check
    check ((abrasion_resistant)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    absorbency            varchar(255)
    constraint product_absorbency_check
    check ((absorbency)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    antistatic            varchar(255)
    constraint product_antistatic_check
    check ((antistatic)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    breathable            varchar(255)
    constraint product_breathable_check
    check ((breathable)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    colorfast             varchar(255)
    constraint product_colorfast_check
    check ((colorfast)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    moisture_transporting varchar(255)
    constraint product_moisture_transporting_check
    check ((moisture_transporting)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    odor_neutralizing     varchar(255)
    constraint product_odor_neutralizing_check
    check ((odor_neutralizing)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    scratch_resistant     varchar(255)
    constraint product_scratch_resistant_check
    check ((scratch_resistant)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    sweat_wicking         varchar(255)
    constraint product_sweat_wicking_check
    check ((sweat_wicking)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    washable              varchar(255)
    constraint product_washable_check
    check ((washable)::text = ANY
((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    breathability         integer,
    flexibility           integer,
    moisture_wicking      integer,
    thickness             real,
    motif                 varchar(255)
    constraint product_motif_check
    check ((motif)::text = ANY
((ARRAY ['NONE'::character varying, 'ANIMALS'::character varying, 'DOTS'::character varying, 'STARS'::character varying, 'TEXT'::character varying, 'IMAGE'::character varying, 'LOGO'::character varying])::text[])),
    neurodermatitis       varchar(255)
    constraint product_neurodermatitis_check
    check ((neurodermatitis)::text = ANY
((ARRAY ['STAGE_ONE_DRY_SKIN'::character varying, 'STAGE_TWO_MILD_ECZEMA'::character varying, 'STAGE_THREE_MODERATE_TO_SEVERE_ECZEMA'::character varying])::text[])),
    oekotex_standard      varchar(255)
    constraint product_oekotex_standard_check
    check ((oekotex_standard)::text = ANY
((ARRAY ['PRODUCT_CLASS_1'::character varying, 'PRODUCT_CLASS_2'::character varying])::text[])),
    amount                numeric(38, 2),
    currency              smallint
    constraint product_currency_check
    check ((currency >= 0) AND (currency <= 2)),
    price_range           varchar(255)
    constraint product_price_range_check
    check ((price_range)::text = ANY
((ARRAY ['STANDARD'::character varying, 'HIGH'::character varying, 'PREMIUM'::character varying])::text[])),
    rating                varchar(255)
    constraint product_rating_check
    check ((rating)::text = ANY
((ARRAY ['FOUR_POINT_FIVE_AND_OVER'::character varying, 'FOUR_POINT_AND_OVER'::character varying, 'THREE_POINT_AND_OVER'::character varying, 'TWO_POINT_AND_OVER'::character varying, 'ONE_POINT_AND_OVER'::character varying])::text[])),
    title                 varchar(255),
    seller_id             uuid
    constraint fkesd6fy52tk7esoo2gcls4lfe3
    references seller
    );

create index if not exists brand_index
    on product (brand);

create table if not exists product_demand
(
    id                    uuid    not null
        primary key,
    created_by            varchar(255),
    created_date          timestamp(6) with time zone,
    last_modified_by      varchar(255),
    last_modified_date    timestamp(6) with time zone,
    adult_age_group       varchar(255)
        constraint product_demand_adult_age_group_check
            check ((adult_age_group)::text = ANY
        ((ARRAY ['NINETEEN_TO_TWENTYNINE'::character varying, 'THIRTY_TO_FOURTYFIVE'::character varying, 'FOURTYSIX_TO_SIXTYSEVEN'::character varying, 'GREATER_THAN_SIXTYESEVEN'::character varying])::text[])),
    children_age_group    varchar(255)
        constraint product_demand_children_age_group_check
            check ((children_age_group)::text = ANY
                   ((ARRAY ['SMALLER_THAN_SIX_MONTHS'::character varying, 'SIX_MONTHS_TO_TWO_YEAR'::character varying, 'TWO_TO_SIX_YEARS'::character varying, 'SEVEN_TO_THIRTEEN_YEARS'::character varying, 'FOURTEEN_TO_EIGHTEEN_YEARS'::character varying])::text[])),
    application_area      varchar(255)
        constraint product_demand_application_area_check
            check ((application_area)::text = ANY
                   ((ARRAY ['PROFESSIONAL_CLOTHING'::character varying, 'SLEEP_AND_REST_TIME'::character varying, 'OUTDOOR'::character varying, 'WOUND_HEALING'::character varying, 'BANDAGE_MATERIAL'::character varying, 'SCRATCH_PROTECTION'::character varying, 'THERAPY_SUPPORT'::character varying, 'SUN_PROTECTION'::character varying, 'HEAT_PROTECTION'::character varying, 'MECHANICAL_PROTECTION'::character varying, 'BED_PROTECTION'::character varying, 'UNDERWEAR'::character varying, 'HOODIES_AND_PULLOVERS'::character varying, 'OCCASION_AND_FESTIVE'::character varying, 'ACCESSORIES_FOR_BABIES_AND_CHILDREN'::character varying, 'PANTS_AND_SHORTS_AND_JUMPSUITS'::character varying, 'SWIMWEAR'::character varying, 'OTHERS'::character varying])::text[])),
    usage_cycle           varchar(255)
        constraint product_demand_usage_cycle_check
            check ((usage_cycle)::text = ANY
                   ((ARRAY ['ONE_TIME'::character varying, 'TWO_TO_NINE'::character varying, 'TEN_TO_FOURTYNINE'::character varying, 'FIFTY_TO_HUNDRED'::character varying, 'MORE_THAN_HUNDRED'::character varying])::text[])),
    brand                 varchar(255),
    category              varchar(255)
        constraint product_demand_category_check
            check ((category)::text = ANY
                   ((ARRAY ['JEANS'::character varying, 'T_SHIRT'::character varying, 'SHIRT'::character varying, 'JACKET'::character varying, 'COAT'::character varying, 'HOODIE'::character varying, 'SWEATSHIRT'::character varying, 'DRESS'::character varying, 'SKIRT'::character varying, 'SHORTS'::character varying, 'PANTS'::character varying, 'LEGGINGS'::character varying, 'JUMPSUIT'::character varying, 'SWIMSUIT'::character varying, 'UNDERWEAR'::character varying, 'SOCKS'::character varying, 'SHOES'::character varying, 'ACCESSORIES'::character varying])::text[])),
    color                 varchar(255)
        constraint product_demand_color_check
            check ((color)::text = ANY
                   ((ARRAY ['RED'::character varying, 'GREEN'::character varying, 'BLUE'::character varying, 'WHITE'::character varying, 'BLACK'::character varying])::text[])),
    design_appearance     varchar(255)
        constraint product_demand_design_appearance_check
            check ((design_appearance)::text = ANY
                   ((ARRAY ['DYNAMIC'::character varying, 'CEREMONIAL'::character varying, 'INNOVATIVE'::character varying, 'CLASSIC'::character varying, 'PRIMITIVE'::character varying, 'PROVOCATIVE'::character varying, 'ROMANTIC'::character varying, 'FACTUAL'::character varying, 'SIMPLE'::character varying, 'STATIC'::character varying, 'TECHNICAL'::character varying, 'TRADITIONAL'::character varying, 'LUXURIOUS'::character varying, 'RESTRAINED'::character varying])::text[])),
    design_color          varchar(255)
        constraint product_demand_design_color_check
            check ((design_color)::text = ANY
                   ((ARRAY ['COLORFUL'::character varying, 'NON_COLORFUL'::character varying, 'PATTERNED'::character varying, 'RAW_WHITE_STRETCHED'::character varying, 'RAW_WHITE_WASHED'::character varying, 'WHITE'::character varying, 'BLACK'::character varying])::text[])),
    gender                varchar(255)
        constraint product_demand_gender_check
            check ((gender)::text = ANY
                   ((ARRAY ['MALE'::character varying, 'FEMALE'::character varying, 'UNISEX'::character varying])::text[])),
    elasticity            varchar(255)
        constraint product_demand_elasticity_check
            check ((elasticity)::text = ANY
                   ((ARRAY ['VERY_ELASTIC'::character varying, 'ELASTIC'::character varying, 'SLIGHTLY_ELASTIC'::character varying, 'SOMEWHAT_STIFF'::character varying, 'VERY_STIFF'::character varying])::text[])),
    fineness              varchar(255)
        constraint product_demand_fineness_check
            check ((fineness)::text = ANY
                   ((ARRAY ['VERY_DELICATE'::character varying, 'VERY_FINE'::character varying, 'FINE'::character varying, 'COARSE'::character varying, 'VERY_COARSE'::character varying])::text[])),
    lightweight           varchar(255)
        constraint product_demand_lightweight_check
            check ((lightweight)::text = ANY
                   ((ARRAY ['ULTRA_LIGHT'::character varying, 'VERY_LIGHT'::character varying, 'LIGHT'::character varying, 'HEAVY'::character varying, 'VERY_HEAVY'::character varying])::text[])),
    lint_free             varchar(255)
        constraint product_demand_lint_free_check
            check ((lint_free)::text = ANY
                   ((ARRAY ['FREE'::character varying, 'ALMOST_FREE'::character varying, 'SOMEWHAT_FREE'::character varying, 'NOT_FREE'::character varying, 'HIGHLY_LINTY'::character varying])::text[])),
    scratchy              varchar(255)
        constraint product_demand_scratchy_check
            check ((scratchy)::text = ANY
                   ((ARRAY ['VERY_SCRATCHY'::character varying, 'SCRATCHY'::character varying, 'ROUGH'::character varying, 'SMOOTH'::character varying, 'VELVETY'::character varying])::text[])),
    seam_feelable         varchar(255)
        constraint product_demand_seam_feelable_check
            check ((seam_feelable)::text = ANY
                   ((ARRAY ['NOT_FEELABLE'::character varying, 'BARELY_FEELABLE'::character varying, 'FEELABLE'::character varying, 'VERY_FEELABLE'::character varying])::text[])),
    softness              varchar(255)
        constraint product_demand_softness_check
            check ((softness)::text = ANY
                   ((ARRAY ['VERY_SOFT'::character varying, 'SOFT'::character varying, 'MODERATE'::character varying, 'HARD'::character varying, 'VERY_HARD'::character varying])::text[])),
    uniform               varchar(255)
        constraint product_demand_uniform_check
            check ((uniform)::text = ANY
                   ((ARRAY ['COMPLETELY'::character varying, 'MOSTLY'::character varying, 'IRREGULAR'::character varying, 'BROKEN'::character varying, 'VERY_UNEVEN'::character varying])::text[])),
    is_active             boolean not null,
    abrasion_resistant    varchar(255)
        constraint product_demand_abrasion_resistant_check
            check ((abrasion_resistant)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    absorbency            varchar(255)
        constraint product_demand_absorbency_check
            check ((absorbency)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    antistatic            varchar(255)
        constraint product_demand_antistatic_check
            check ((antistatic)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    breathable            varchar(255)
        constraint product_demand_breathable_check
            check ((breathable)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    colorfast             varchar(255)
        constraint product_demand_colorfast_check
            check ((colorfast)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    moisture_transporting varchar(255)
        constraint product_demand_moisture_transporting_check
            check ((moisture_transporting)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    odor_neutralizing     varchar(255)
        constraint product_demand_odor_neutralizing_check
            check ((odor_neutralizing)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    scratch_resistant     varchar(255)
        constraint product_demand_scratch_resistant_check
            check ((scratch_resistant)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    sweat_wicking         varchar(255)
        constraint product_demand_sweat_wicking_check
            check ((sweat_wicking)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    washable              varchar(255)
        constraint product_demand_washable_check
            check ((washable)::text = ANY
                   ((ARRAY ['VERY'::character varying, 'MODERATE'::character varying, 'SOMEWHAT'::character varying, 'BARELY'::character varying, 'NOT_AT_ALL'::character varying])::text[])),
    breathability         integer,
    flexibility           integer,
    moisture_wicking      integer,
    thickness             real,
    motif                 varchar(255)
        constraint product_demand_motif_check
            check ((motif)::text = ANY
                   ((ARRAY ['NONE'::character varying, 'ANIMALS'::character varying, 'DOTS'::character varying, 'STARS'::character varying, 'TEXT'::character varying, 'IMAGE'::character varying, 'LOGO'::character varying])::text[])),
    neurodermatitis       varchar(255)
        constraint product_demand_neurodermatitis_check
            check ((neurodermatitis)::text = ANY
                   ((ARRAY ['STAGE_ONE_DRY_SKIN'::character varying, 'STAGE_TWO_MILD_ECZEMA'::character varying, 'STAGE_THREE_MODERATE_TO_SEVERE_ECZEMA'::character varying])::text[])),
    oekotex_standard      varchar(255)
        constraint product_demand_oekotex_standard_check
            check ((oekotex_standard)::text = ANY
                   ((ARRAY ['PRODUCT_CLASS_1'::character varying, 'PRODUCT_CLASS_2'::character varying])::text[])),
    price_range           varchar(255)
        constraint product_demand_price_range_check
            check ((price_range)::text = ANY
                   ((ARRAY ['STANDARD'::character varying, 'HIGH'::character varying, 'PREMIUM'::character varying])::text[])),
    rating                varchar(255)
        constraint product_demand_rating_check
            check ((rating)::text = ANY
                   ((ARRAY ['FOUR_POINT_FIVE_AND_OVER'::character varying, 'FOUR_POINT_AND_OVER'::character varying, 'THREE_POINT_AND_OVER'::character varying, 'TWO_POINT_AND_OVER'::character varying, 'ONE_POINT_AND_OVER'::character varying])::text[])),
    status                smallint
        constraint product_demand_status_check
            check ((status >= 0) AND (status <= 3)),
    customer_id           uuid    not null
        constraint fk9whyf7kpwwta1cxndslqjm72g
            references customer,
    seller_id             uuid
        constraint fk6g8g0e4ml2twsmh6pgq977rt9
            references seller
);

create table if not exists order_table
(
    id                    uuid         not null
        primary key,
    created_by            varchar(255),
    created_date          timestamp(6) with time zone,
    last_modified_by      varchar(255),
    last_modified_date    timestamp(6) with time zone,
    cargo_price           numeric(38, 2),
    delivery_address      varchar(255),
    delivery_address_type varchar(255),
    delivery_status       varchar(255)
        constraint order_table_delivery_status_check
            check ((delivery_status)::text = ANY
        ((ARRAY ['ORDER_PLACED'::character varying, 'ORDER_CONFIRMED'::character varying, 'SHIPPED'::character varying, 'DELIVERED'::character varying, 'CANCELED'::character varying])::text[])),
    delivery_status_date  timestamp(6),
    delivery_type         varchar(255),
    is_visible            boolean      not null,
    order_date            timestamp(6) not null,
    order_number          varchar(255) not null,
    order_utc_date        timestamp(6) not null,
    total_discount        numeric(38, 2),
    total_price           numeric(38, 2),
    customer_id           uuid         not null
        constraint fkdit09e676nqbguvt1k1w8mlj2
            references customer
);



create table if not exists cart_item
(
    id         uuid not null
        primary key,
    quantity   bigint,
    cart_id    uuid
        constraint fk1uobyhgl1wvgt1jpccia8xxs3
            references cart,
    product_id uuid
        constraint fkjcyd5wv4igqnw413rgxbfu4nv
            references product
);

create table if not exists customer_allergies_sensitivities
(
    customer_id             uuid not null
        constraint fke1jw5aujql1flwuy296ou80lg
            references customer,
    allergies_sensitivities varchar(255)
);

create table if not exists customer_body_regions
(
    customer_id  uuid not null
        constraint fk19ay7oe5dnirhornfaef4e9qe
            references customer,
    body_regions varchar(255)
        constraint customer_body_regions_body_regions_check
            check ((body_regions)::text = ANY
        ((ARRAY ['SCALP'::character varying, 'FACE'::character varying, 'CHEEKS'::character varying, 'EAR_SHELL'::character varying, 'EAR_CANAL'::character varying, 'NECK'::character varying, 'ARMPITS'::character varying, 'ELBOWS'::character varying, 'BACK_OF_HAND'::character varying, 'TOENAILS'::character varying, 'CHEST'::character varying, 'ABDOMEN'::character varying, 'NAVEL'::character varying, 'GROIN'::character varying, 'BUTTOCKS'::character varying, 'GENITAL_AREA'::character varying, 'KNEES'::character varying, 'FOOT_BACK'::character varying])::text[]))
    );

create table if not exists customer_clothing_selection
(
    customer_id        uuid not null
        constraint fkliv0x8ko3kting6mwpqbrjq1u
            references customer,
    clothing_selection varchar(255)
);

create table if not exists customer_medical_history
(
    customer_id     uuid not null
        constraint fk8kchcasnag1et3ctkb28eotxk
            references customer,
    medical_history varchar(255)
);

create table if not exists customer_past_health_issues
(
    customer_id        uuid not null
        constraint fk98pongnxld3sc22xmfp6ql5le
            references customer,
    past_health_issues varchar(255)
);

create table if not exists customer_treatment_with_medications
(
    customer_id                uuid not null
        constraint fk6euq554v4yc8xvgbdopamnugv
            references customer,
    treatment_with_medications varchar(255)
);

create table if not exists ordered_product
(
    id                 uuid not null
        primary key,
    created_by         varchar(255),
    created_date       timestamp(6) with time zone,
    last_modified_by   varchar(255),
    last_modified_date timestamp(6) with time zone,
    image_url          varchar(255),
    amount             numeric(38, 2),
    currency           smallint
        constraint ordered_product_currency_check
            check ((currency >= 0) AND (currency <= 2)),
    product_id         uuid,
    quantity           bigint,
    title              varchar(255),
    order_id           uuid
        constraint fk1me38f635rnieslow7t4htl72
            references order_table,
    seller_id          uuid
        constraint fkech7qsxvdye3tb2ihsnsnvlab
            references seller
);

create table if not exists product_active_substance_areas
(
    product_id             uuid not null
        constraint fkf1i5qi84s6oks42e36af1gcls
            references product,
    active_substance_areas varchar(255)
        constraint product_active_substance_areas_active_substance_areas_check
            check ((active_substance_areas)::text = ANY
        ((ARRAY ['COMPLETE'::character varying, 'AREA_SPECIFIC'::character varying])::text[]))
    );

create table if not exists product_active_substance_placements
(
    product_id                  uuid not null
        constraint fkc5oiu4bsl3m2x2mbvde5yexqt
            references product,
    active_substance_placements varchar(255)
        constraint product_active_substance_plac_active_substance_placements_check
            check ((active_substance_placements)::text = ANY
        ((ARRAY ['FILAMENT_SURFACE'::character varying, 'AREA_EQUIPMENT'::character varying, 'ENCAPSULATION'::character varying, 'TABLETS'::character varying, 'SERUM'::character varying, 'FIBER_BASED'::character varying, 'FOULARD_COATING'::character varying, 'MICROCAPSULE_COATING'::character varying])::text[]))
    );

create table if not exists product_active_substance_releases
(
    product_id                uuid not null
        constraint fkasmi0fiiyr81c5s9sae49gmtm
            references product,
    active_substance_releases varchar(255)
        constraint product_active_substance_releas_active_substance_releases_check
            check ((active_substance_releases)::text = ANY
        ((ARRAY ['SWEAT_TRIGGERED'::character varying, 'FRICITION_TRIGGERED'::character varying, 'DIFFUSING'::character varying])::text[]))
    );

create table if not exists product_active_substances
(
    product_id        uuid not null
        constraint fkirp1g78xr0ed5mr6i29aihk3w
            references product,
    active_substances varchar(255)
        constraint product_active_substances_active_substances_check
            check ((active_substances)::text = ANY
        ((ARRAY ['CHITOSAN'::character varying, 'BROWN_ALGAE_EXTRACT'::character varying, 'NANO_SILVER'::character varying, 'SILK_FIBROIN'::character varying, 'POLYTETRAFLUOROETHYLENE'::character varying, 'ZINC_OXIDE'::character varying, 'NEMOLIZUMAB'::character varying, 'CORTISONE'::character varying, 'GRAVIOLA'::character varying, 'CANNABIS'::character varying])::text[]))
    );

create table if not exists product_certifications
(
    product_id     uuid not null
        constraint fkd8otr0ai12dypef9n5lkvu4ki
            references product,
    certifications varchar(255)
        constraint product_certifications_certifications_check
            check ((certifications)::text = ANY
        ((ARRAY ['FLAME_RETARDANT'::character varying, 'BETTER_COTTON_INITIATIVE'::character varying, 'BLAUER_ENGEL'::character varying, 'BLUESIGN'::character varying, 'BUSINESS_SOCIAL_COMPLIANCE_INITIATIVE'::character varying, 'ALLIANCE_FOR_SUSTAINABLE_TEXTILES'::character varying, 'CRADLE_TO_CRADLE'::character varying, 'COTTON_MADE_IN_AFRICA'::character varying, 'EAC_CERTIFICATION_OF_CLOTHING_AND_SHOES'::character varying, 'ETHICAL_TRADING_INITIATIVE'::character varying, 'EU_ECOLABEL'::character varying, 'GLOBAL_ORGANIC_TEXTILE_STANDARD'::character varying, 'GLOBAL_RECYCLE_STANDARD'::character varying, 'FAIRTRADE_CERTIFIED_COTTON'::character varying, 'FAIR_WEAR_FOUNDATION'::character varying, 'GREEN_BUTTON'::character varying, 'ISO_14000_ENVIRONMENTAL_MANAGEMENT'::character varying, 'ISO_26000_SUSTAINABILITY'::character varying, 'IVN_NATURAL_TEXTILES'::character varying, 'NATURLAND_SEAL'::character varying, 'ORGANIC_CONTENT_STANDARD'::character varying, 'OEKO_TEX_STANDARD'::character varying, 'OEKO_TEX_1000_STEP'::character varying, 'OEKO_TEX_100_PLUS'::character varying, 'SEAL_MEDICALLY_TESTED_AND_POLLUTANT_TESTED'::character varying, 'SA_8000'::character varying, 'TOXPROOF'::character varying, 'UN_GLOBA_COMPACT'::character varying, 'CERTIFICATION_MADE_IN_GERMANY'::character varying])::text[]))
    );

create table if not exists product_compositions
(
    product_id   uuid not null
        constraint fk8b6rio82ikfbxux9uosba1m3h
            references product,
    compositions varchar(255)
        constraint product_compositions_compositions_check
            check ((compositions)::text = ANY
        ((ARRAY ['MULTI_LAYERED'::character varying, 'COMPOSITE'::character varying, 'PATCHWORK'::character varying])::text[]))
    );

create table if not exists product_demand_active_substance_areas
(
    product_demand_id      uuid not null
        constraint fkmkhtjr21iyt39okg4wbgg076l
            references product_demand,
    active_substance_areas varchar(255)
        constraint product_demand_active_substance_ar_active_substance_areas_check
            check ((active_substance_areas)::text = ANY
        ((ARRAY ['COMPLETE'::character varying, 'AREA_SPECIFIC'::character varying])::text[]))
    );

create table if not exists product_demand_active_substance_placements
(
    product_demand_id           uuid not null
        constraint fkqart1bylx4kknqof0cs66ee40
            references product_demand,
    active_substance_placements varchar(255)
        constraint product_demand_active_substan_active_substance_placements_check
            check ((active_substance_placements)::text = ANY
        ((ARRAY ['FILAMENT_SURFACE'::character varying, 'AREA_EQUIPMENT'::character varying, 'ENCAPSULATION'::character varying, 'TABLETS'::character varying, 'SERUM'::character varying, 'FIBER_BASED'::character varying, 'FOULARD_COATING'::character varying, 'MICROCAPSULE_COATING'::character varying])::text[]))
    );

create table if not exists product_demand_active_substances
(
    product_demand_id uuid not null
        constraint fkkci4laprgtg3uql9luoiu6jd3
            references product_demand,
    active_substances varchar(255)
        constraint product_demand_active_substances_active_substances_check
            check ((active_substances)::text = ANY
        ((ARRAY ['CHITOSAN'::character varying, 'BROWN_ALGAE_EXTRACT'::character varying, 'NANO_SILVER'::character varying, 'SILK_FIBROIN'::character varying, 'POLYTETRAFLUOROETHYLENE'::character varying, 'ZINC_OXIDE'::character varying, 'NEMOLIZUMAB'::character varying, 'CORTISONE'::character varying, 'GRAVIOLA'::character varying, 'CANNABIS'::character varying])::text[]))
    );

create table if not exists product_demand_certifications
(
    product_demand_id uuid not null
        constraint fkq39h7ra17ltkh0vkeee7qgf5b
            references product_demand,
    certifications    varchar(255)
        constraint product_demand_certifications_certifications_check
            check ((certifications)::text = ANY
        ((ARRAY ['FLAME_RETARDANT'::character varying, 'BETTER_COTTON_INITIATIVE'::character varying, 'BLAUER_ENGEL'::character varying, 'BLUESIGN'::character varying, 'BUSINESS_SOCIAL_COMPLIANCE_INITIATIVE'::character varying, 'ALLIANCE_FOR_SUSTAINABLE_TEXTILES'::character varying, 'CRADLE_TO_CRADLE'::character varying, 'COTTON_MADE_IN_AFRICA'::character varying, 'EAC_CERTIFICATION_OF_CLOTHING_AND_SHOES'::character varying, 'ETHICAL_TRADING_INITIATIVE'::character varying, 'EU_ECOLABEL'::character varying, 'GLOBAL_ORGANIC_TEXTILE_STANDARD'::character varying, 'GLOBAL_RECYCLE_STANDARD'::character varying, 'FAIRTRADE_CERTIFIED_COTTON'::character varying, 'FAIR_WEAR_FOUNDATION'::character varying, 'GREEN_BUTTON'::character varying, 'ISO_14000_ENVIRONMENTAL_MANAGEMENT'::character varying, 'ISO_26000_SUSTAINABILITY'::character varying, 'IVN_NATURAL_TEXTILES'::character varying, 'NATURLAND_SEAL'::character varying, 'ORGANIC_CONTENT_STANDARD'::character varying, 'OEKO_TEX_STANDARD'::character varying, 'OEKO_TEX_1000_STEP'::character varying, 'OEKO_TEX_100_PLUS'::character varying, 'SEAL_MEDICALLY_TESTED_AND_POLLUTANT_TESTED'::character varying, 'SA_8000'::character varying, 'TOXPROOF'::character varying, 'UN_GLOBA_COMPACT'::character varying, 'CERTIFICATION_MADE_IN_GERMANY'::character varying])::text[]))
    );

create table if not exists product_demand_compositions
(
    product_demand_id uuid not null
        constraint fkc8lhtirydiuskweg3d1sk6t7h
            references product_demand,
    compositions      varchar(255)
        constraint product_demand_compositions_compositions_check
            check ((compositions)::text = ANY
        ((ARRAY ['MULTI_LAYERED'::character varying, 'COMPOSITE'::character varying, 'PATCHWORK'::character varying])::text[]))
    );

create table if not exists product_demand_design_body_parts
(
    product_demand_id uuid not null
        constraint fkknvv415dv029uib7y0obdp57l
            references product_demand,
    design_body_parts varchar(255)
        constraint product_demand_design_body_parts_design_body_parts_check
            check ((design_body_parts)::text = ANY
        ((ARRAY ['SEAMLESS'::character varying, 'EDGELESS'::character varying, 'FLAT_SEAMS'::character varying, 'FLAT_EDGES'::character varying, 'FLAT_CUFFS'::character varying])::text[]))
    );

create table if not exists product_demand_environmental_compatibilities
(
    product_demand_id             uuid not null
        constraint fksqhiw3cw9aosuelom7l5ileev
            references product_demand,
    environmental_compatibilities varchar(255)
        constraint product_demand_environmental_environmental_compatibilitie_check
            check ((environmental_compatibilities)::text = ANY
        ((ARRAY ['ENVIRONMENTAL_COMPATIBILITY'::character varying, 'FREE_OF_POLLUTANTS'::character varying, 'LOW_WATER_POLLUTION'::character varying, 'LOW_SOIL_POLLUTION'::character varying, 'LOW_AIR_POLLUTION'::character varying, 'PRESERVATION_OF_BIODIVERSITY'::character varying, 'HARMLESS_PEST_CONTROL'::character varying, 'BIODEGRADABLE'::character varying])::text[]))
    );

create table if not exists product_demand_fiber_types
(
    product_demand_id uuid not null
        constraint fk7e3sdl8qsrc9f937dt1pnlj02
            references product_demand,
    fiber_types       varchar(255)
        constraint product_demand_fiber_types_fiber_types_check
            check ((fiber_types)::text = ANY
        ((ARRAY ['COTTON'::character varying, 'POLYESTER'::character varying, 'VISCOSE'::character varying, 'SILK'::character varying, 'ELASTANE'::character varying, 'MODAL'::character varying, 'LYOCELL'::character varying, 'PROTEIN'::character varying, 'SKIN_TO_SKIN_FIBER'::character varying, 'TENCEL'::character varying])::text[]))
    );

create table if not exists product_demand_life_cycles
(
    product_demand_id uuid not null
        constraint fkglrmfqbs2w5emjgqk8dp05kf0
            references product_demand,
    life_cycles       varchar(255)
        constraint product_demand_life_cycles_life_cycles_check
            check ((life_cycles)::text = ANY
        ((ARRAY ['LIFE_CYCLE'::character varying, 'CIRCULAR_ECONOMY'::character varying, 'SUSTAINABLE_RAW_MATERIALS'::character varying, 'ESPECIALLY_DURABLE'::character varying, 'RECYCLABILITY'::character varying, 'REPAIRABILITY'::character varying, 'MANY_CLEANING_CYCLES'::character varying])::text[]))
    );

create table if not exists product_demand_regionality_list
(
    product_demand_id uuid not null
        constraint fkb3agk3sd20fkxrp27hqacux9y
            references product_demand,
    regionality_list  varchar(255)
        constraint product_demand_regionality_list_regionality_list_check
            check ((regionality_list)::text = ANY
        ((ARRAY ['REGIONALITY'::character varying, 'MADE_IN_GERMANY'::character varying, 'REGIONAL_YARN_SUPPLY'::character varying, 'CIRCULAR_KNITTING_IN_REGION'::character varying, 'FINISHING_IN_REGION'::character varying, 'RECYCLING_IN_REGION'::character varying])::text[]))
    );

create table if not exists product_demand_resource_consumptions
(
    product_demand_id     uuid not null
        constraint fkp1y2qcspcibxgkcrw3dl7uy89
            references product_demand,
    resource_consumptions varchar(255)
        constraint product_demand_resource_consumption_resource_consumptions_check
            check ((resource_consumptions)::text = ANY
        ((ARRAY ['RESOURCE_CONSUMPTION'::character varying, 'SUSTAINABLE_LAND_USE'::character varying, 'LOW_ENERGY'::character varying, 'LOW_WATER'::character varying, 'LOW_RAW_MATERIALS'::character varying, 'LOW_CHEMICALS'::character varying, 'LOW_USAGE'::character varying])::text[]))
    );

create table if not exists product_demand_sizes
(
    product_demand_id uuid not null
        constraint fkn5o8iduc6kq7lpx108vfkrrnc
            references product_demand,
    sizes             varchar(255)
        constraint product_demand_sizes_sizes_check
            check ((sizes)::text = ANY
        ((ARRAY ['XXS'::character varying, 'XS'::character varying, 'S'::character varying, 'M'::character varying, 'L'::character varying, 'XL'::character varying])::text[]))
    );

create table if not exists product_demand_skills
(
    product_demand_id uuid not null
        constraint fkn8qx4jcqp93hvfi73gb8d1fg9
            references product_demand,
    skills            varchar(255)
        constraint product_demand_skills_skills_check
            check ((skills)::text = ANY
        ((ARRAY ['YARN_SUPPLY'::character varying, 'WOVEN_FABRIC'::character varying, 'FINISHING'::character varying, 'PROTOTYPE'::character varying, 'PRODUCT_PARAMETER_MODELING'::character varying, 'MODELING_SUPPORT'::character varying, 'CO_DESIGN'::character varying, 'PATTERN_CREATION'::character varying, 'CUTTING'::character varying, 'ASSEMBLY'::character varying, 'ACTIVE_SUBSTANCE_PLACEMENT'::character varying, 'QUALITY_CONTROL'::character varying, 'B2C'::character varying, 'RECYCLING'::character varying])::text[]))
    );

create table if not exists product_demand_social_ethics
(
    product_demand_id uuid not null
        constraint fkbs1ednpp6hj3m0hettwbspump
            references product_demand,
    social_ethics     varchar(255)
        constraint product_demand_social_ethics_social_ethics_check
            check ((social_ethics)::text = ANY
        ((ARRAY ['SOCIAL_ETHICS'::character varying, 'SOCIAL_STANDARDS'::character varying, 'HYGIENE_STANDARDS'::character varying, 'FAIR_WAGES'::character varying, 'NO_CHILD_LABOUR'::character varying, 'HIGH_TRANSPARENCY'::character varying])::text[]))
    );

create table if not exists product_demand_specific_body_parts
(
    product_demand_id   uuid not null
        constraint fkfd4b0t54vkjhcy9ns8mydcdq1
            references product_demand,
    specific_body_parts varchar(255)
        constraint product_demand_specific_body_parts_specific_body_parts_check
            check ((specific_body_parts)::text = ANY
        ((ARRAY ['SCALP'::character varying, 'FACE'::character varying, 'CHEEKS'::character varying, 'EAR_SHELL'::character varying, 'EAR_CANAL'::character varying, 'NECK'::character varying, 'ARMPITS'::character varying, 'ELBOWS'::character varying, 'BACK_OF_HAND'::character varying, 'TOENAILS'::character varying, 'CHEST'::character varying, 'ABDOMEN'::character varying, 'NAVEL'::character varying, 'GROIN'::character varying, 'BUTTOCKS'::character varying, 'GENITAL_AREA'::character varying, 'KNEES'::character varying, 'FOOT_BACK'::character varying])::text[]))
    );

create table if not exists product_demand_specific_functionalities
(
    product_demand_id        uuid not null
        constraint fkm9veyglwjf721k8caw8l7fsea
            references product_demand,
    specific_functionalities varchar(255)
        constraint product_demand_specific_function_specific_functionalities_check
            check ((specific_functionalities)::text = ANY
        ((ARRAY ['ANTIBACTERIAL'::character varying, 'ANTIVIRAL'::character varying, 'COOLING'::character varying, 'LIGHT_TRANSMITTING'::character varying, 'UV_PROTECTIVE'::character varying, 'WINDPROOF'::character varying, 'ITCH_RELIEVING'::character varying, 'SKIN_SOOTHING'::character varying, 'SWEAT_ABSORBENT'::character varying, 'ODOR_NEUTRALIZING'::character varying])::text[]))
    );

create table if not exists product_demand_staggerings
(
    product_demand_id uuid not null
        constraint fk93y466d0nipcn8k4g2b8537f5
            references product_demand,
    staggerings       varchar(255)
        constraint product_demand_staggerings_staggerings_check
            check ((staggerings)::text = ANY
        ((ARRAY ['TEMPORAL'::character varying, 'SPATIAL'::character varying, 'INTERVAL'::character varying])::text[]))
    );

create table if not exists product_demand_sustainability_compositions
(
    product_demand_id           uuid not null
        constraint fkp74kc0y0vs1ukcocos8enr4qq
            references product_demand,
    sustainability_compositions varchar(255)
        constraint product_demand_sustainability_sustainability_compositions_check
            check ((sustainability_compositions)::text = ANY
        ((ARRAY ['COMPOSITION'::character varying, 'USABILITY'::character varying, 'FORGERY_PROTECTION'::character varying, 'RARE_MATERIALS'::character varying, 'COMBINATION_SMART_TEXTILES'::character varying, 'INTEGRATION_IDENTIFICATION'::character varying])::text[]))
    );

create table if not exists product_demand_sustainability_lightweights
(
    product_demand_id           uuid not null
        constraint fkm79ncwgnu2ru88iueeq5gkja2
            references product_demand,
    sustainability_lightweights varchar(255)
        constraint product_demand_sustainability_sustainability_lightweights_check
            check ((sustainability_lightweights)::text = ANY
        ((ARRAY ['LIGHTWEIGHT'::character varying, 'ULTRALIGHT'::character varying, 'VERY_LIGHT'::character varying, 'LIGHT'::character varying, 'HEAVY'::character varying, 'VERY_HEAVY'::character varying])::text[]))
    );

create table if not exists product_design_body_parts
(
    product_id        uuid not null
        constraint fkp0seq05evfojgvjm4b9xcigl6
            references product,
    design_body_parts varchar(255)
        constraint product_design_body_parts_design_body_parts_check
            check ((design_body_parts)::text = ANY
        ((ARRAY ['SEAMLESS'::character varying, 'EDGELESS'::character varying, 'FLAT_SEAMS'::character varying, 'FLAT_EDGES'::character varying, 'FLAT_CUFFS'::character varying])::text[]))
    );

create table if not exists product_environmental_compatibilities
(
    product_id                    uuid not null
        constraint fkqndmgw3jsexs6405o5xa15sqc
            references product,
    environmental_compatibilities varchar(255)
        constraint product_environmental_compat_environmental_compatibilitie_check
            check ((environmental_compatibilities)::text = ANY
        ((ARRAY ['ENVIRONMENTAL_COMPATIBILITY'::character varying, 'FREE_OF_POLLUTANTS'::character varying, 'LOW_WATER_POLLUTION'::character varying, 'LOW_SOIL_POLLUTION'::character varying, 'LOW_AIR_POLLUTION'::character varying, 'PRESERVATION_OF_BIODIVERSITY'::character varying, 'HARMLESS_PEST_CONTROL'::character varying, 'BIODEGRADABLE'::character varying])::text[]))
    );

create table if not exists product_fiber_types
(
    product_id  uuid not null
        constraint fk6q0d2uvyxu2y8hyy8sd0wy5ms
            references product,
    fiber_types varchar(255)
        constraint product_fiber_types_fiber_types_check
            check ((fiber_types)::text = ANY
        ((ARRAY ['COTTON'::character varying, 'POLYESTER'::character varying, 'VISCOSE'::character varying, 'SILK'::character varying, 'ELASTANE'::character varying, 'MODAL'::character varying, 'LYOCELL'::character varying, 'PROTEIN'::character varying, 'SKIN_TO_SKIN_FIBER'::character varying, 'TENCEL'::character varying])::text[]))
    );

create table if not exists product_life_cycles
(
    product_id  uuid not null
        constraint fk8l8jnd0f3yfejy2xaaitsuyp5
            references product,
    life_cycles varchar(255)
        constraint product_life_cycles_life_cycles_check
            check ((life_cycles)::text = ANY
        ((ARRAY ['LIFE_CYCLE'::character varying, 'CIRCULAR_ECONOMY'::character varying, 'SUSTAINABLE_RAW_MATERIALS'::character varying, 'ESPECIALLY_DURABLE'::character varying, 'RECYCLABILITY'::character varying, 'REPAIRABILITY'::character varying, 'MANY_CLEANING_CYCLES'::character varying])::text[]))
    );

create table if not exists product_regionality_list
(
    product_id       uuid not null
        constraint fkkimtngqqh7cy2bn1pmwlxmd38
            references product,
    regionality_list varchar(255)
        constraint product_regionality_list_regionality_list_check
            check ((regionality_list)::text = ANY
        ((ARRAY ['REGIONALITY'::character varying, 'MADE_IN_GERMANY'::character varying, 'REGIONAL_YARN_SUPPLY'::character varying, 'CIRCULAR_KNITTING_IN_REGION'::character varying, 'FINISHING_IN_REGION'::character varying, 'RECYCLING_IN_REGION'::character varying])::text[]))
    );

create table if not exists product_resource_consumptions
(
    product_id            uuid not null
        constraint fk91f6yi7ddoo0jmcpmdpjp65uq
            references product,
    resource_consumptions varchar(255)
        constraint product_resource_consumptions_resource_consumptions_check
            check ((resource_consumptions)::text = ANY
        ((ARRAY ['RESOURCE_CONSUMPTION'::character varying, 'SUSTAINABLE_LAND_USE'::character varying, 'LOW_ENERGY'::character varying, 'LOW_WATER'::character varying, 'LOW_RAW_MATERIALS'::character varying, 'LOW_CHEMICALS'::character varying, 'LOW_USAGE'::character varying])::text[]))
    );

create table if not exists product_sizes
(
    product_id uuid not null
        constraint fk4w69qsh5hd062xv3hqkpgpdpu
            references product,
    sizes      varchar(255)
        constraint product_sizes_sizes_check
            check ((sizes)::text = ANY
        ((ARRAY ['XXS'::character varying, 'XS'::character varying, 'S'::character varying, 'M'::character varying, 'L'::character varying, 'XL'::character varying])::text[]))
    );

create table if not exists product_skills
(
    product_id uuid not null
        constraint fkc45y7v3syywurocpclnk3lpa9
            references product,
    skills     varchar(255)
        constraint product_skills_skills_check
            check ((skills)::text = ANY
        ((ARRAY ['YARN_SUPPLY'::character varying, 'WOVEN_FABRIC'::character varying, 'FINISHING'::character varying, 'PROTOTYPE'::character varying, 'PRODUCT_PARAMETER_MODELING'::character varying, 'MODELING_SUPPORT'::character varying, 'CO_DESIGN'::character varying, 'PATTERN_CREATION'::character varying, 'CUTTING'::character varying, 'ASSEMBLY'::character varying, 'ACTIVE_SUBSTANCE_PLACEMENT'::character varying, 'QUALITY_CONTROL'::character varying, 'B2C'::character varying, 'RECYCLING'::character varying])::text[]))
    );

create table if not exists product_social_ethics
(
    product_id    uuid not null
        constraint fkq8f5dy7t3w14lfe0e3pumit8
            references product,
    social_ethics varchar(255)
        constraint product_social_ethics_social_ethics_check
            check ((social_ethics)::text = ANY
        ((ARRAY ['SOCIAL_ETHICS'::character varying, 'SOCIAL_STANDARDS'::character varying, 'HYGIENE_STANDARDS'::character varying, 'FAIR_WAGES'::character varying, 'NO_CHILD_LABOUR'::character varying, 'HIGH_TRANSPARENCY'::character varying])::text[]))
    );

create table if not exists product_specific_body_parts
(
    product_id          uuid not null
        constraint fk8rdb98fgtp68rt08d412jrrvq
            references product,
    specific_body_parts varchar(255)
        constraint product_specific_body_parts_specific_body_parts_check
            check ((specific_body_parts)::text = ANY
        ((ARRAY ['SCALP'::character varying, 'FACE'::character varying, 'CHEEKS'::character varying, 'EAR_SHELL'::character varying, 'EAR_CANAL'::character varying, 'NECK'::character varying, 'ARMPITS'::character varying, 'ELBOWS'::character varying, 'BACK_OF_HAND'::character varying, 'TOENAILS'::character varying, 'CHEST'::character varying, 'ABDOMEN'::character varying, 'NAVEL'::character varying, 'GROIN'::character varying, 'BUTTOCKS'::character varying, 'GENITAL_AREA'::character varying, 'KNEES'::character varying, 'FOOT_BACK'::character varying])::text[]))
    );

create table if not exists product_specific_functionalities
(
    product_id               uuid not null
        constraint fke7iokdg5qq08n3nyrqb6kq0sx
            references product,
    specific_functionalities varchar(255)
        constraint product_specific_functionalities_specific_functionalities_check
            check ((specific_functionalities)::text = ANY
        ((ARRAY ['ANTIBACTERIAL'::character varying, 'ANTIVIRAL'::character varying, 'COOLING'::character varying, 'LIGHT_TRANSMITTING'::character varying, 'UV_PROTECTIVE'::character varying, 'WINDPROOF'::character varying, 'ITCH_RELIEVING'::character varying, 'SKIN_SOOTHING'::character varying, 'SWEAT_ABSORBENT'::character varying, 'ODOR_NEUTRALIZING'::character varying])::text[]))
    );

create table if not exists product_staggerings
(
    product_id  uuid not null
        constraint fkjhbhe67cuv37rm3vjj1smnxr2
            references product,
    staggerings varchar(255)
        constraint product_staggerings_staggerings_check
            check ((staggerings)::text = ANY
        ((ARRAY ['TEMPORAL'::character varying, 'SPATIAL'::character varying, 'INTERVAL'::character varying])::text[]))
    );

create table if not exists product_sustainability_compositions
(
    product_id                  uuid not null
        constraint fkay72s4h7fk6pa3qmdih13of1q
            references product,
    sustainability_compositions varchar(255)
        constraint product_sustainability_compos_sustainability_compositions_check
            check ((sustainability_compositions)::text = ANY
        ((ARRAY ['COMPOSITION'::character varying, 'USABILITY'::character varying, 'FORGERY_PROTECTION'::character varying, 'RARE_MATERIALS'::character varying, 'COMBINATION_SMART_TEXTILES'::character varying, 'INTEGRATION_IDENTIFICATION'::character varying])::text[]))
    );

create table if not exists product_sustainability_lightweights
(
    product_id                  uuid not null
        constraint fkcv1hek45jcqwm1hyk0oedy4bk
            references product,
    sustainability_lightweights varchar(255)
        constraint product_sustainability_lightw_sustainability_lightweights_check
            check ((sustainability_lightweights)::text = ANY
        ((ARRAY ['LIGHTWEIGHT'::character varying, 'ULTRALIGHT'::character varying, 'VERY_LIGHT'::character varying, 'LIGHT'::character varying, 'HEAVY'::character varying, 'VERY_HEAVY'::character varying])::text[]))
    );

create table if not exists seller_application_areas
(
    seller_id         uuid not null
        constraint fkcqxgjjj8a7gi9o791dp4pjusq
            references seller,
    application_areas varchar(255)
        constraint seller_application_areas_application_areas_check
            check ((application_areas)::text = ANY
        ((ARRAY ['PROFESSIONAL_CLOTHING'::character varying, 'SLEEP_AND_REST_TIME'::character varying, 'OUTDOOR'::character varying, 'WOUND_HEALING'::character varying, 'BANDAGE_MATERIAL'::character varying, 'SCRATCH_PROTECTION'::character varying, 'THERAPY_SUPPORT'::character varying, 'SUN_PROTECTION'::character varying, 'HEAT_PROTECTION'::character varying, 'MECHANICAL_PROTECTION'::character varying, 'BED_PROTECTION'::character varying, 'UNDERWEAR'::character varying, 'HOODIES_AND_PULLOVERS'::character varying, 'OCCASION_AND_FESTIVE'::character varying, 'ACCESSORIES_FOR_BABIES_AND_CHILDREN'::character varying, 'PANTS_AND_SHORTS_AND_JUMPSUITS'::character varying, 'SWIMWEAR'::character varying, 'OTHERS'::character varying])::text[]))
    );

create table if not exists seller_skills
(
    seller_id uuid not null
        constraint fkbocnoph5lmh4l972xtga4whc5
            references seller,
    skills    varchar(255)
        constraint seller_skills_skills_check
            check ((skills)::text = ANY
        ((ARRAY ['YARN_SUPPLY'::character varying, 'WOVEN_FABRIC'::character varying, 'FINISHING'::character varying, 'PROTOTYPE'::character varying, 'PRODUCT_PARAMETER_MODELING'::character varying, 'MODELING_SUPPORT'::character varying, 'CO_DESIGN'::character varying, 'PATTERN_CREATION'::character varying, 'CUTTING'::character varying, 'ASSEMBLY'::character varying, 'ACTIVE_SUBSTANCE_PLACEMENT'::character varying, 'QUALITY_CONTROL'::character varying, 'B2C'::character varying, 'RECYCLING'::character varying])::text[]))
    );

create table if not exists flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

create index if not exists flyway_schema_history_s_idx
    on flyway_schema_history (success);
