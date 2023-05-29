mod icpc {
    pub mod na_reg_22 {
        pub mod blueberry_waffle;
        pub mod fading_wind;
        pub mod restaurant_opening;
        pub mod streets_ahead;
    }
}

pub fn call_solution(solution: &str) {
    match solution {
        "blueberry_waffle" => icpc::na_reg_22::blueberry_waffle::main(),
        "fading_wind" => icpc::na_reg_22::fading_wind::main(),
        "restaurant_opening" => icpc::na_reg_22::restaurant_opening::main(),
        "restaurant_opening_n2" => icpc::na_reg_22::restaurant_opening::main_n2(),
        "streets_ahead" => icpc::na_reg_22::streets_ahead::main(),
        _ => println!("No solution for {}", solution),
    }
}
