# Hibernate Associations

Consider two entities, `Singer` and `Song`, involved in a bidirectional lazy 
`@OneToMany` association.

![](fig-1.png)

Singer - bidirectional `@OneToMany` - Song: 
- singer row can be referenced by multiple songs.
- `singer_id` column maps `@OneToMany` relationship  via a foreign key that references the primary key of the singer table.
- song cannot exist without a singer, 
therefore, the singer is the parent side (@OneToMany) while     

- 
