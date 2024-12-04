ALTER TABLE product_fiber_types
DROP CONSTRAINT product_fiber_types_fiber_types_check;

ALTER TABLE product_fiber_types
    ADD CONSTRAINT product_fiber_types_fiber_types_check
        CHECK ((fiber_types)::text = ANY
    ((ARRAY [
    'COTTON'::character varying,
    'POLYESTER'::character varying,
    'VISCOSE'::character varying,
    'SILK'::character varying,
    'ELASTANE'::character varying,
    'MODAL'::character varying,
    'LYOCELL'::character varying,
    'PROTEIN'::character varying,
    'SKIN_TO_SKIN_FIBER'::character varying,
    'TENCEL'::character varying,
    'WOOL'::character varying,
    'SISAL'::character varying
    ])::text[]));

ALTER TABLE product_demand_fiber_types
DROP CONSTRAINT product_demand_fiber_types_fiber_types_check;

ALTER TABLE product_demand_fiber_types
    ADD CONSTRAINT product_demand_fiber_types_fiber_types_check
        CHECK ((fiber_types)::text = ANY
    ((ARRAY [
    'COTTON'::character varying,
    'POLYESTER'::character varying,
    'VISCOSE'::character varying,
    'SILK'::character varying,
    'ELASTANE'::character varying,
    'MODAL'::character varying,
    'LYOCELL'::character varying,
    'PROTEIN'::character varying,
    'SKIN_TO_SKIN_FIBER'::character varying,
    'TENCEL'::character varying,
    'WOOL'::character varying,
    'SISAL'::character varying
    ])::text[]));