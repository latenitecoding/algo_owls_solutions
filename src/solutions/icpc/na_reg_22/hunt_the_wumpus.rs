use std::{fmt::Debug, io, str::FromStr};

//============================================
// StdIn Helpers
//============================================

#[allow(unused)]
#[inline(always)]
fn next<T: FromStr>(buffer: &mut String) -> T
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    buffer.trim().parse::<T>().unwrap()
}

#[allow(unused)]
#[inline(always)]
fn next_tuple<T: FromStr>(buffer: &mut String) -> (T, T)
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    let mut iter = buffer.trim().split(' ').map(|s| s.parse::<T>().unwrap());
    (iter.next().unwrap(), iter.next().unwrap())
}

#[allow(unused)]
#[inline(always)]
fn next_vec<T: FromStr>(buffer: &mut String) -> Vec<T>
where
    <T as FromStr>::Err: Debug,
{
    io::stdin().read_line(buffer).unwrap();
    buffer
        .trim()
        .split(' ')
        .map(|s| s.parse::<T>().unwrap())
        .collect::<Vec<T>>()
}

//============================================
// Solution
//============================================

pub fn main() {
    let mut buffer = String::new();
    let mut s = next::<u32>(&mut buffer);

    let mut wumpuses = [(0i8, 0i8); 4];
    let mut grid = [[0u8; 10]; 10];

    let mut count = 0;
    while count < 4 {
        s += s / 13 + 15;
        let (x, y) = (s % 100 / 10, s % 10);
        if grid[x as usize][y as usize] == 1 {
            continue;
        }
        wumpuses[count] = (x as i8, y as i8);
        grid[x as usize][y as usize] = 1;
        count += 1;
    }

    let mut m = 0;

    while count > 0 {
        buffer.clear();
        let guess = next::<u8>(&mut buffer);
        let (x, y) = (guess / 10, guess % 10);
        m += 1;

        if grid[x as usize][y as usize] == 1 {
            println!("You hit a wumpus!");
            grid[x as usize][y as usize] = 0;
            count -= 1;

            if count == 0 {
                break;
            }
        }

        let dist = wumpuses
            .iter()
            .filter(|wumpus| grid[wumpus.0 as usize][wumpus.1 as usize] == 1)
            .map(|wumpus| (wumpus.0 - x as i8).abs() + (wumpus.1 - y as i8).abs())
            .min()
            .unwrap();
        println!("{}", dist);
    }

    println!("Your score is {} moves.", m);
}
