import React, { useState, useEffect } from "react";
import axios from "axios";
import ArticleCard from "../components/ArticleCard.tsx";

interface Article {
  id: string;
  title: string;
  content: string;
  imageUrl?: string;
}

const Articles: React.FC = () => {
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [articles, setArticles] = useState<Article[]>([]);

  useEffect(() => {
    const fetchArticles = async () => {
      setLoading(true);
      try {
        const response = await axios.get<Article[]>("http://localhost:8080/api/articles?page=0&size=5");
        console.log(response.data);
        setArticles(response.data.content);
        setError(null);
      } catch (err) {
        console.error("Błąd podczas pobierania artykułów:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchArticles();
  }, []);

  if (loading) {
    return <p className="text-center text-gray-800">Ładowanie artykułów...</p>;
  }

  if (error) {
    return <p className="text-center text-red-500">Wystąpił błąd: {error}</p>;
  }

  if (articles.length === 0) {
    return <p className="text-center text-gray-500">Nie znaleziono żadnych artykułów.</p>;
  }

  return (
    <div className="container mx-auto p-4">
      <h1 className="text-3xl font-bold text-center text-gray-800">Artykuły</h1>
      <input
          type="text"
          placeholder="Szukaj artykułów po tytule lub treści..."
          className="w-full p-3 my-4 bg-white border border-gray-300 rounded-md focus:border-stone-800 focus:outline-none text-stone-950"
      />
      <div className="flex flex-col gap-6">
        {articles.map(article => (
          <ArticleCard
            key={article.id}
            id={article.id}
            title={article.title}
            content={article.content}
            imageUrl={article.imageUrl}
          />
        ))}
      </div>
    </div>
  );
};

export default Articles;