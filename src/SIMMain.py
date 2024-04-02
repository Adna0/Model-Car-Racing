import subprocess
import itertools

#DEFINE YOUR PARAMETERS HERE!
parameters = {
    'engine_power': [100, 200, 300],
    'wheel_diameter': [0.05, 0.1]

}

#CODE STARTS HERE!
param_combinations = [{param: value for param, value in zip(parameters.keys(), combo)}
                      for combo in itertools.product(*parameters.values())]

output_file = open("simulation_results.txt", "w")
for i, params in enumerate(param_combinations):
    command = ['java', 'SimulationClass']
    for param, value in params.items():
        command.append(str(value))

    try:
        print(f"Running simulation {i+1}/{len(param_combinations)} with parameters: {params}")
        subprocess.run(command, check=True)
        output_file.write(f"Simulation {i+1}/{len(param_combinations)} with parameters: {params} - Success\n")
    except subprocess.CalledProcessError as e:
        exception_message = str(e)
        output_file.write(f"Simulation {i+1}/{len(param_combinations)} with: {params} - Exception: {exception_message}\n")

output_file.close()