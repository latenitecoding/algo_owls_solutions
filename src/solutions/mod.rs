mod icpc {
    pub mod na_reg_22 {
        pub mod blueberry_waffle;
        pub mod champernowne_count;
        pub mod fading_wind;
        pub mod hunt_the_wumpus;
        pub mod repetitive_song;
        pub mod restaurant_opening;
        pub mod streets_ahead;
        pub mod sun_and_moon;
    }
}

pub fn call_solution(solution: &str) {
    match solution {
        "blueberry_waffle" => icpc::na_reg_22::blueberry_waffle::main(),
        "champernowne_count" => icpc::na_reg_22::champernowne_count::main(),
        "fading_wind" => icpc::na_reg_22::fading_wind::main(),
        "hunt_the_wumpus" => icpc::na_reg_22::hunt_the_wumpus::main(),
        "repetitive_song" => icpc::na_reg_22::repetitive_song::main(),
        "restaurant_opening" => icpc::na_reg_22::restaurant_opening::main(),
        "restaurant_opening_n2" => icpc::na_reg_22::restaurant_opening::main_n2(),
        "streets_ahead" => icpc::na_reg_22::streets_ahead::main(),
        "sun_and_moon" => icpc::na_reg_22::sun_and_moon::main(),
        _ => println!("No solution for {}", solution),
    }
}
