ini_load .algo_owls.ini

if [[ -z ${args[--no_build]} || ${args[--no_build]} -eq 0 ]]; then
    if [[ ${ini[settings.auto_build]} == true ]]; then
        ./algo_owls build ${args[solution]}
    elif [[ -n ${args[--build]} && ${args[--build]} -eq 1 ]]; then
        ./algo_owls build ${args[solution]}
    fi
fi

file_ext="$(handle_file_ext)"

solution_file="${args[solution]}"
if [[ -n $file_ext && -n ${args[--ext]} ]]; then
    solution_file="$solution_file$file_ext"
elif [[ -z ${args[--no_ext]} || ${args[--no_ext]} -eq 0 ]]; then
    if [[ -n ${ini[run.file_ext]} ]]; then
        file_ext="${ini[run.file_ext]}"
        if [[ -n $file_ext && ${file_ext:0:1} != "." ]]; then
            file_ext=".$file_ext"
        fi
    fi
    if [[ -n $file_ext && ${ini[run.no_ext]} == false ]]; then
        solution_file="$solution_file$file_ext"
    fi
fi

if [[ -n ${args[--use_source]} && ${args[--use_source]} -eq 1 ]]; then
    target_file="$(find ${ini[options.solutions_dir]} -name $solution_file)"
else
    target_file="$(find ${ini[options.target_dir]} -name $solution_file)"
fi

if [[ -z $target_file ]]; then
    echo "algo_owls: $solution_file: No such file or directory" 1>&2
    target_file="${ini[options.solutions_dir]}/${args[solution]}"
    echo "Try using: ./algo_owls init $target_file" 1>&2
    exit 1
fi

run_cmd="${ini[run.cmd]}"
if [[ -n ${args[--run_cmd]} ]]; then
    run_cmd="${args[--run_cmd]}"
fi

if [[ -n ${args[--run_flags]} ]]; then
    run_cmd="$run_cmd ${args[--run_flags]}"
else
    for key in "${!ini[@]}"; do
        if [[ $key == run_flags.* ]]; then
            run_cmd="$run_cmd ${ini[$key]}"
        fi
    done
fi

if [[ -n ${args[--run_sources]} ]]; then
    run_sources="${args[--run_sources]}"
    if [[ -n ${args[--local]} && ${args[--local]} -eq 1 ]]; then
        target_dir="${target_file%/*}"
        run_sources="${run_sources//.../$target_dir}"
    elif [[ -n ${args[--use_source]} && ${args[--use_source]} -eq 1 ]]; then
        target_dir="${target_file%/*}"
        run_sources="${run_sources//.../$target_dir}"
    fi
    run_cmd="$run_cmd $run_sources"
fi

for key in "${!ini[@]}"; do
    if [[ $key == options.* ]]; then
        run_cmd="${run_cmd//$key/${ini[$key]}}"
    fi
done

run_cmd="$run_cmd $target_file"

eval $run_cmd
