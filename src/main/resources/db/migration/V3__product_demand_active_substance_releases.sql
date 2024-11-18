create table if not exists product_demand_active_substance_releases
(
    product_demand_id         uuid not null
        constraint fk2279wp06vo084iohucvkuypa
            references product_demand,
    active_substance_releases varchar(255)
        constraint product_demand_active_substance_active_substance_releases_check
            check ((active_substance_releases)::text = ANY
        ((ARRAY ['SWEAT_TRIGGERED'::character varying, 'FRICITION_TRIGGERED'::character varying, 'DIFFUSING'::character varying])::text[]))
    );
