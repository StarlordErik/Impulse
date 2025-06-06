package de.seleri.spielelemente

interface ToYaml {
    fun objToYaml(): String
    fun yamlToObj(yamlInput: String): Any
}
