package com.yarin.pokemon;

import com.yarin.pokemon.repository.PokemonRepository;
import com.yarin.pokemon.repository.TrainerRepository;
import com.yarin.pokemon.models.*;
import com.yarin.pokemon.repository.RoleRepository;
import com.yarin.pokemon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class PokemonServerApplication implements ApplicationRunner {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private
	UserRepository userRepository;

	@Autowired
	private
	TrainerRepository trainerRepository;

	@Autowired
	private
	PokemonRepository pokemonRepository;

	public static void main(String[] args) {
		SpringApplication.run(PokemonServerApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		CacheManager cacheManager = CacheManager.getInstance();
		List<Pokemon> pokemonList = pokemonRepository.findAll();
		cacheManager.loadPokemons(pokemonList);
		List<User> userList = userRepository.findAll();
		cacheManager.loadUsers(userList);
		List<Trainer> trainersList = trainerRepository.findAll();
		cacheManager.loadTrainers(trainersList);


	/*	data.removeAllPokemons();
		List<Pokemon> list = new LinkedList<>();
		list.add(new Pokemon(1,"bulbasaur",PokemonType.Grass));
		list.add(new Pokemon(37,"vulpix",PokemonType.Fire));
		list.add(new Pokemon(6,"charizard",PokemonType.Fire));
		list.add(new Pokemon(126,"magmar",PokemonType.Fire));
		list.add(new Pokemon(154,"cyndaquil",PokemonType.Fire));
		list.add(new Pokemon(653,"fennekin",PokemonType.Fire));
		list.add(new Pokemon(7,"squirtel",PokemonType.Water));
		list.add(new Pokemon(134,"vaporeon",PokemonType.Water));
		list.add(new Pokemon(55,"golduck",PokemonType.Water));
		list.add(new Pokemon(71,"victreeble",PokemonType.Grass));
		list.add(new Pokemon(152,"chikorita",PokemonType.Grass));
		list.add(new Pokemon(131,"lapras",PokemonType.Water));
		mongoTemplate.insertAll(list);
*/
	/*	data.removeAllTrainers();
		List<Trainer> tlist = new LinkedList<>();
		Trainer t = new Trainer("ash");
		t.setLevel(1);
		tlist.add(t);
		t=new Trainer("gary");
		t.setLevel(1);
		tlist.add(t);
		t=new Trainer("misty");
		t.setLevel(1);
		tlist.add(t);
		mongoTemplate.insertAll(tlist);
		Pokedax p = Pokedax.getInstance();

		List<Pokemon> pokemonList = data.getPokemons();
		p.loadPokemons(pokemonList);
		List<Trainer> trainersList = data.getTrainers();
		p.loadTrainers(trainersList);
		Pokemon pokemon = p.getPokemonById(1);
		Trainer trainer = p.getTrainer("Ash".toLowerCase());

		int index = trainer.addPokemon(pokemon);
		data.addPokemon(trainer,pokemon,index);
		 index = trainer.addPokemon(pokemon);
		data.addPokemon(trainer,pokemon,index);
		 index = trainer.addPokemon(pokemon);
		data.addPokemon(trainer,pokemon,index);
		trainer = p.getTrainer("Misty".toLowerCase());
		 index = trainer.addPokemon(pokemon);
		data.addPokemon(trainer,pokemon,index);
		index = trainer.addPokemon(pokemon);
		data.addPokemon(trainer,pokemon,index);
		index = trainer.addPokemon(pokemon);
		data.addPokemon(trainer,pokemon,index);

*/

	}

}
