package ua.pp.salnikov.magazine_test_task.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "color", nullable = false)
    @Enumerated(EnumType.STRING)
    private ArticleColor color;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

}
