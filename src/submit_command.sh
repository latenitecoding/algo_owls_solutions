ini_load .algo_owls.ini

file_ext="$(handle_file_ext)"

solution_file="${args[solution]}"
if [[ -n $file_ext ]]; then
    solution_file="$solution_file$file_ext"
fi

target_file="$(find ${ini[options.solutions_dir]} -name $solution_file)"

if [[ -z $target_file ]]; then
    echo "algo_owls: $solution_file: No such file or directory" 1>&2
    target_file="${ini[options.solutions_dir]}/${args[solution]}"
    echo "Try using: ./algo_owls init $target_file" 1>&2
    exit 1
fi

if [[ -z ${args[--no_fmt]} || ${args[--no_fmt]} -eq 0 ]]; then
    if [[ ${ini[settings.auto_fmt]} == true ]]; then
        ./algo_owls fmt ${args[solution]}
    elif [[ -n ${args[--fmt]} && ${args[--fmt]} -eq 1 ]]; then
        ./algo_owls fmt ${args[solution]}
    fi
fi

git add $target_file

commit_message="completes ${args[solution]}"
if [[ -n ${args[--message]} ]]; then
    commit_message="${args[--message]}"
fi

git commit -m $commit_message

branch="${ini[git.branch]}"
if [[ -n ${args[--branch]} ]]; then
    branch="${args[--branch]}"
fi

remote="${ini[git.remote]}"
if [[ -n ${args[--remote]} ]]; then
    remote="${args[--remote]}"
fi

git push $remote $branch